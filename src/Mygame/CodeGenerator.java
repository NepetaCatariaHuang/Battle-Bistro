package Mygame;

public class CodeGenerator {
	
	public static void main (String[] args) {
		
		
	/*	
		for(int i=0;i<10;i++) {
			for(int j=0;j<20;j++) {
				System.out.println("<ImageView fx:id=\"h"+i+"_"+j+"\" fitHeight=\"60.0\" fitWidth=\"47.0\" layoutX=\""+(0.0+47*i)+
						"\" layoutY=\""+(0.0+41*j)+"\">\r\n" + 
						"               <image>\r\n" + 
						"                  <Image url=\"@high.png\" />\r\n" + 
						"               </image>\r\n" + 
						"            </ImageView>");
			}*/
			 
		
		/*for(int i=0;i<20;i++) {
				System.out.println("<HBox layoutX=\"605.0\" layoutY=\""+(30.0+i*41)+"\" prefHeight=\"60.0\" prefWidth=\"472.0\" />");
		*/
		
		
		for(int i=0;i<20;i++) {
			System.out.print("@FXML HBox HB"+i+"; ");
		
		

			
		}
	}

}
