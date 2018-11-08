
import java.awt.Color; 
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.util.*;
public class XYLineChart_AWT extends ApplicationFrame {
	Random rand = new Random();
   public XYLineChart_AWT( String applicationTitle, String Base, String attribute, String chartTitle ,double[] base,double[] att1,double[] att2,double[] att3,double[] att4,int size ) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         Base ,
         attribute ,
         createDataset(base,att1,att2,att3,att4,size ) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
 
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.BLUE );
      renderer.setSeriesPaint( 2 ,Color.YELLOW );
      renderer.setSeriesPaint( 3 ,Color.black );
      renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      renderer.setSeriesStroke( 3 , new BasicStroke( 2.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
    private XYDataset createDataset(double[] base,double[] att1,double[] att2, double[] att3,double[] att4,int size) {
        final XYSeries avgFitness = new XYSeries( "avgFitness" );                   
        final XYSeries avgbest10 = new XYSeries( "avgbest10" ); 
        final XYSeries Fitness = new XYSeries( "Fitness" ); 
        final XYSeries bestchrom = new XYSeries( "bestchromo" ); 

        for(int i=0;i<size;i++)
        {
            avgFitness.add(base[i],att1[i]);
            avgbest10.add(base[i],att2[i]); 
            Fitness.add(base[i],att3[i]);
            bestchrom.add(base[i],att4[i]);
        }	        
        final XYSeriesCollection dataset = new XYSeriesCollection( );          
        dataset.addSeries( avgFitness );
        dataset.addSeries( avgbest10 );
        dataset.addSeries( Fitness );          
        dataset.addSeries(bestchrom);
        return dataset;
    }

   public static void Draw( String applicationTitle, String Base, String attribute, String chartTitle ,double[] base,double[] att1,double[] att2,double[] att3,double[] att4,int size) {
      XYLineChart_AWT chart = new XYLineChart_AWT(applicationTitle, Base, attribute, chartTitle ,base,att1,att2,att3,att4,size );
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }
}
