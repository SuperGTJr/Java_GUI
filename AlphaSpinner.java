import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AlphaSpinner extends JSpinner {
    Mediator mediator;
    String type;
    
    public AlphaSpinner(Mediator mediator, String type) {
        super(new SpinnerNumberModel(100, 0, 100, 1));

        addChangeListener(new AlphaChangeListener());
        
        this.mediator = mediator;
        this.type = type;
        
     // 選択された図形の透明度に応じてスピナの初期値を設定
//        if (type == "FILL")) {
//            int fillAlpha = mediator.getFillAlpha();
//            int alphaPercentage = fillAlpha * 100 / 255;
//            setValue(alphaPercentage);
//        } else if (type == "LINE") {
//            int lineAlpha = mediator.getLineAlpha();
//            int alphaPercentage = lineAlpha * 100 / 255;
//            setValue(alphaPercentage);
//        }

    }

    class AlphaChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int alphaPercentage = (int) getValue();
            int alpha = alphaPercentage * 255 /100;
            if(type == "FILL") {
            	 mediator.setFillAlpha(alpha);
                 mediator.repaint();
            }
            else if(type == "LINE") {
            	mediator.setLineAlpha(alpha);
            	mediator.repaint();
            }
        }
    }
}
