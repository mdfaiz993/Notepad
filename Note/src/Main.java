import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

class Note extends JFrame implements ActionListener{


    JFrame f;
    //textarea
    JTextArea t;
    //menubar
    JMenuBar menubar;
    //constructor
    Note(){
        //initialize frames
        f = new JFrame("Notepad");
        //Initialize textarea
        t = new JTextArea();
        //create menubar
        menubar = new JMenuBar();

        //create menu

        JMenu file = new JMenu("File");
         //creating file items

        JMenuItem f1 = new JMenuItem("New");
        JMenuItem f2 = new JMenuItem("Save");
        JMenuItem f3 = new JMenuItem("Open");
        JMenuItem f4 = new JMenuItem("Print");
        // Adding actionlistener to all the menu options....
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        //adding to file menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);

        //create edit
        JMenu edit = new JMenu("Edit");
        //creating file items

        JMenuItem e1 = new JMenuItem("Cut");
        JMenuItem e2 = new JMenuItem("Copy");
        JMenuItem e3 = new JMenuItem("Paste");

        // Adding actionlistener to all the menu options....
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);


        //adding to edit menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);

        //create close item
        JMenuItem close = new JMenuItem("Close");

        close.addActionListener(this);


        //adding to menu bar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(close);

        //adding menubar to frame
        f.setJMenuBar(menubar);
        f.add(t);

        f.setSize(1280,720);
        f.show();







    }






    // to all the functionalities
    public void actionperformed(ActionEvent e){

    }
    public static void main(String[] args){
        Note n = new Note();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Extracting commnd into a String
        String s = e.getActionCommand();

        switch(s){
            case "New":
                t.setText("");
                break;
            case "Save":
                //Creating a starting path of Jfile Drive to save and open
                JFileChooser j = new JFileChooser("F:");
                //invoke the dialog box
                int r = j.showSaveDialog(null);
                // if r = 1 save else cancel
                if(r == 0) {
                    // declare a file object and store location
                    File fi = new File(j.getSelectedFile().getAbsolutePath());
                    try {
                        FileWriter fw = new FileWriter(fi);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(t.getText());

                        bw.flush();
                        bw.close();


                    }
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                }
                else{
                    JOptionPane.showMessageDialog(f,"the user has cancelled the program");
                }

                break;
            case "Open":
                //Creating a starting path of Jfile Drive to save and open
                JFileChooser ji = new JFileChooser("F:");
                //invoke the dialog box
                int ri = ji.showOpenDialog(null);
                // if r = 1 save else cancel
                if(ri == 0) {
                    // declare a file object and store location
                    File fi = new File(ji.getSelectedFile().getAbsolutePath());
                    try {
                        FileReader fw = new FileReader(fi);
                        BufferedReader br = new BufferedReader(fw);

                        String s1 = "", s2 = "" ;
                        //first line stored in s1
                        while((s2 = br.readLine()) != null)
                        {
                            s1 = s1 + "/n" + s2 ;
                        }
                        //for all the contain is copied to s1

                        t.setText(s1);

                    }
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                }
                else{
                    JOptionPane.showMessageDialog(f,"the user has cancelled the program");
                }

                break;
            case "Print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;
            default:
                throw new IllegalStateException("Unexcepted value :" + s);
        }
    }
}