package main;

import ui.VentanaPrincipal;

public class MainUI {
	public static void main(String[]args) {
		
	       javax.swing.SwingUtilities.invokeLater(() -> {
	            new VentanaPrincipal().setVisible(true);
	        });
		
		
		
	}

}
