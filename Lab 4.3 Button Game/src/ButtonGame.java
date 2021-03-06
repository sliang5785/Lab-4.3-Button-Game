import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ButtonGame extends Application 
{
	private int score = 0;
	private boolean scoring = true;
	private long timeStep;
	private int highScore=0;

	
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Click me!");
        Button btn = new Button();
        btn.setText("Say 'Click me!'");
        Text txt = new Text(10,0, "Click Score");
        Label label = new Label(" ");
        Label label1 = new Label(" ");
        Label label2 = new Label(" ");
        txt.setStyle("-fx-font: normal bold 12px 'monospace' "); 
        label.setStyle("-fx-font: normal bold 12px 'monospace' "); 
        label1.setStyle("-fx-font: normal bold 12px 'monospace' "); 
        label2.setStyle("-fx-font: normal bold 11px 'monospace' "); 
        btn.setStyle("-fx-font: normal 12px 'monospace' "); 
        btn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
       
        
        btn.setOnAction(new EventHandler<ActionEvent>() 
        
        {
            @Override
            public void handle(ActionEvent event) 
            {
            	
               if(scoring) 
               {
            	   score++;
            	   btn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            	   if(score> highScore) 
            	   {
                  	   highScore++;
                   }
               }
               else 
               {
            	   btn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            	  score--;
               }
            }
        });
        
        timeStep = System.nanoTime() + 10000000000L;
        new AnimationTimer()
        {
        	
        	public void handle(long now)
        	{
        		if(now > timeStep)
        		{
        			timeStep = now + 10000000000L;
        			scoring = !scoring;
        		}
        		if(!scoring)
        		{
        			btn.setText("Don't click!");
        			
        			label1.setText("Wait for it.... Don't click....");
        			label2.setText("Wait 10 seconds after this timer starts: " + Integer.toString((int) (now / 1000000000)) );
        			
        			// Integer.toString(  (int) ( ((x / 1000000000)+10)-(now / 1000000000))  )  );
        		}

        		else
        		{
        			btn.setText("Click me");
        			label1.setText("You have 10 seconds to click! Go!");
        			label2.setText("You have 10 seconds after the starting time: " + Integer.toString((int) (now / 1000000000)));
        		}
                 
        		txt.setText("SCORE: " + Integer.toString(score));
        		label.setText("HIGH SCORE: " + Integer.toString(highScore));
            }
        }.start();
        
       
     
      
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(txt);
        root.setAlignment(txt, Pos.TOP_LEFT);
        root.setAlignment(label, Pos.TOP_RIGHT);
        label1.setTranslateX(-10);
        label1.setTranslateY(-75);
        root.setAlignment(label2, Pos.BOTTOM_CENTER);
        root.getChildren().add(label);
        root.getChildren().add(label1);
        root.getChildren().add(label2);
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }
}