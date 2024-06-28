import javax.swing.JOptionPane;
public class ChoosingOption {
    public static void main(String[] args){
        int option = JOptionPane.showConfirmDialog(null,
                "Do you want to change the first class ticket?");
        JOptionPane.showMessageDialog(null, "You've chosen: "
                + (option == JOptionPane.YES_OPTION? "Yes":"No"));
        System.exit(0);


        //int option = JOptionPane.showConfirmDialog(null,
        //        "Do you want to change the first class ticket?", "Customize text",
        //        JOptionPane.YES_NO_OPTION);
        //JOptionPane.showMessageDialog(null, "You've chosen: "
        //        + (option == JOptionPane.YES_OPTION? "Yes":"No"));
        //System.exit(0);


        //int option = JOptionPane.showOptionDialog(null,
        //        "Do you want to change the first class ticket?", "Customize text",
        //        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"I do", "I don't"}, "I do");
        //JOptionPane.showMessageDialog(null, "You've chosen: "
        //        + (option == JOptionPane.YES_OPTION? "I do":"I don't"));
        //System.exit(0);
    }
}
