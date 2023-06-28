import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
//import javax.swing.JTabbedPane;

public class MyApplication extends JFrame{
	StateManager stateManager;
	MyCanvas canvas;
	private JMenuBar menuBar;
	private JMenu colorMenu;
	
	public MyApplication() {
		super("My Paint Application");
		
		canvas = new MyCanvas();
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		
		stateManager = new StateManager(canvas.mediator);
		stateManager.setState(new RectState(stateManager));
		
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
    	jp.add(ovalButton);
		TriangleButton triangleButton = new TriangleButton(stateManager);
		jp.add(triangleButton);
		HendecagonalButton hendecagonalButton = new HendecagonalButton(stateManager);
		jp.add(hendecagonalButton);
		SelectButton selectButton = new SelectButton(stateManager);
		jp.add(selectButton);
		
		//メニュー
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		colorMenu = new JMenu("Color");
		JMenu colorLine = new JMenu("Line");
		JMenu otherLineColor = new JMenu("Other");
		JMenu colorFill = new JMenu("Fill");
		JMenu otherFillColor = new JMenu("Other");
		
		ColorItem lineRedItem = new ColorItem(canvas.mediator, Color.red, "red", "LINE");
		ColorItem lineBlueItem = new ColorItem(canvas.mediator, Color.blue, "blue", "LINE");
		ColorItem lineGreenItem = new ColorItem(canvas.mediator, Color.green, "green", "LINE");
		ColorChooser lineChooser = new ColorChooser(canvas.mediator, "LINE");
		otherLineColor.add(lineChooser);
		
		ColorItem fillRedItem = new ColorItem(canvas.mediator, Color.red, "red", "FILL");
		ColorItem fillBlueItem = new ColorItem(canvas.mediator, Color.blue, "blue", "FILL");
		ColorItem fillGreenItem = new ColorItem(canvas.mediator, Color.green, "green", "FILL");
		ColorChooser fillChooser = new ColorChooser(canvas.mediator, "FILL");
		otherFillColor.add(fillChooser);
		
		colorLine.add(lineRedItem);
		colorLine.add(lineBlueItem);
		colorLine.add(lineGreenItem);
		colorLine.add(otherLineColor);
		
		colorFill.add(fillRedItem);
		colorFill.add(fillBlueItem);
		colorFill.add(fillGreenItem);
		colorFill.add(otherFillColor);
		
		colorMenu.add(colorLine);
		colorMenu.add(colorFill);
		
		menuBar.add(colorMenu);
		
		DashCheck dashCheck = new DashCheck(canvas.mediator);
		jp.add(dashCheck);
		ShadowCheck shadowCheck = new ShadowCheck(canvas.mediator);
		jp.add(shadowCheck);
		
//		DashLengthSpinner dashLengthSpinner = new DashLengthSpinner(stateManager);
//		jp.add(dashLengthSpinner);
		
		JLabel linesLabel = new JLabel("Lines:");
		jp.add(linesLabel);
		LineSpinner lineSpinner = new LineSpinner(canvas.mediator);
		jp.add(lineSpinner);
		
		JLabel lineWidthLabel = new JLabel("Width:");
		jp.add(lineWidthLabel);
		LineWidthSpinner lineWidthSpinner = new LineWidthSpinner(canvas.mediator);
		jp.add(lineWidthSpinner);
		
//Panelで機能を分けられたら素敵ですね
//		JPanel shapePanel = new JPanel();
//        shapePanel.setLayout(new FlowLayout());
//        shapePanel.add(rectButton);
//        shapePanel.add(ovalButton);
//        shapePanel.add(triangleButton);
//        shapePanel.add(hendecagonalButton);
//		
//		JPanel optionPanel = new JPanel();
//	    optionPanel.setLayout(new FlowLayout());
//	    optionPanel.add(dashCheck);
//	    optionPanel.add(shadowCheck);
//
//	    JTabbedPane tabbedPane = new JTabbedPane();
//	    tabbedPane.addTab("Shapes", shapePanel);
//	    tabbedPane.addTab("Options", optionPanel);
//	    
//	    getContentPane().setLayout(new BorderLayout());
//        getContentPane().add(tabbedPane, BorderLayout.NORTH);
//        getContentPane().add(canvas, BorderLayout.CENTER);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);
		
		canvas.addMouseListener(new MouseAdapter() {
			//現在の状態のmouseDown処理の呼び出し
			public void mousePressed(MouseEvent e) {
				stateManager.mouseDown(e.getX(), e.getY());
				canvas.mediator.repaint();
			}
			public void mouseReleased(MouseEvent e) {
				stateManager.mouseUp(e.getX(), e.getY());
				canvas.mediator.repaint();
			}
		});
		
		canvas.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				stateManager.mouseDrag(e.getX(), e.getY());
			    canvas.mediator.repaint();
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			//ウインドウを閉じたら終了
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1000, 800);
	}
	
	public static void main(String args[]) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}
}