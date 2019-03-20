/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hackaton;

/**
 *
 * @author av7521l
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Demo extends JFrame implements MenuListener, ActionListener, KeyListener {

    JPanel txtpnl = new JPanel(new BorderLayout());
    JTextField txtShowText = new JTextField(20);
    
    private final JComboBox crseCombo;
    
    private final JButton b = new JButton("Add a course"); // button for adding a course


    //JButton MyButton = new JButton("title change"); do not need this (Delete later)
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtNewNote = new JTextArea();
    JTextArea txtDisplayNotes = new JTextArea();
    ArrayList<String> note = new ArrayList<>();
    /// String for JComboBox
    String[] courses = {"COMP-1752", "COMP-1753",
        "COMP-1765", "COMP-1771", "MATH-1111" 
    }; 

    ///Menu Bar///
    JMenuBar menuBar;
    /// JMenu is menu that has sub menus -\-\-\-\-\-
    JMenu crseMenu, crsePick, crseOp, exit, crseNote, crseSaveNote;
    /// JMenuItem is the end product (with the Icons)
    JMenuItem crseAmen, crseAdd, crseDel;
    JMenuItem crseNoteNew, crseNoteClear, crseNoteDel;

    public static void main(String[] args) {

        //JOptionPane.showMessageDialog(null, "andy wicks - wa 02");
        Demo prg = new Demo();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("NewNote".equals(ae.getActionCommand())) {
            txtShowText.setText("This is a note.");
        }
        if ("Close".equals(ae.getActionCommand())) {
            txtShowText.setText("");
        }
        /* if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
            if (ae.getSource() == MyButton) {
                setTitle("Don't Slack");
            }
        }*/
    }

    protected JMenuItem makeMenuItem(
            String txt,
            String actionCommand,
            String toolTipText,
            Font fnt) {
        JMenuItem mnuItem = new JMenuItem();
        mnuItem.setText(txt);
        mnuItem.setActionCommand(actionCommand);
        mnuItem.setToolTipText(toolTipText);
        mnuItem.setFont(fnt);
        mnuItem.addActionListener(this);
        return mnuItem;
    }

    public Demo() {
        this.crseCombo = new JComboBox(courses);
        model();
        view();
        controller();
    }

    private void model() {
        note.add("Arrays are of fixed length and are inflexible.");
        note.add("ArraysList can be added to and items can be deleted.");
    }

    private void view() {
        Font fnt = new Font("Georgia", Font.PLAIN, 24);
        JPanel pnlTop = new JPanel();

        ///menu bar//---------------------------------------------------
        menuBar = new JMenuBar();

        /// The first heading on the menu bar.///
        crseMenu = new JMenu("Courses");
        crseMenu.addMenuListener(this);
        menuBar.add(crseMenu);

        /// Notes menu Item.///
        crseNote = new JMenu("Notes");
        crseNote.addMenuListener(this);
        menuBar.add(crseNote);

        /// Save Note menu Item.///
        crseSaveNote = new JMenu("Save Note");
        crseSaveNote.addMenuListener(this);
        menuBar.add(crseSaveNote);

        /* /// Exit menu item.///
        exit = new JMenu("Exit");
        exit.setMnemonic(KeyEvent.VK_X); /// Exits the program when "X" is pressed
        exit.addMenuListener(this);
        menuBar.add(exit);*/
        /// Adding a sub-menu to crseMenu
        crsePick = new JMenu("Select a Course");
        //    crsePick.addMenuListener(new thisMenuListener());
        crseMenu.add(crsePick);

        /// Adding another sub-menu to crseMenu.
        crseOp = new JMenu("Course Options");
        //   crseOp.addMenuListener(new thisMenuListener());
        crseMenu.add(crseOp);

        /// Adding a sub-menu item to crse, Find an Icon later...
        crseAmen = new JMenuItem("Amend Courses",
                new ImageIcon(""));
        crseAmen.addActionListener(this);
        crseOp.add(crseAmen);

        /// Another sub-menu item, Find an Icon later...
        crseAdd = new JMenuItem("Add a course",
                new ImageIcon(""));
        crseAdd.addActionListener(this);
        crseOp.add(crseAdd);

        /// Another sub-menu item, Find an Icon later...
        crseDel = new JMenuItem("Delete a Course",
                new ImageIcon(""));
        crseDel.addActionListener(this);
        crseOp.add(crseDel);

        /// Adding a sub-menu item to crseNote, Find an Icon later...
        crseNoteNew = new JMenuItem("New Notes",
                new ImageIcon(""));
        crseNoteNew.addActionListener(this);
        crseNote.add(crseNoteNew);

        /// Adding a sub-menu item to crseNote, Find an Icon later...
        crseNoteClear = new JMenuItem("Clear Notes",
                new ImageIcon(""));
        crseNoteClear.addActionListener(this);
        crseNote.add(crseNoteClear);

        /// Adding a sub-menu item to crseNote, Find an Icon later...
        crseNoteDel = new JMenuItem("Delete Notes",
                new ImageIcon(""));
        crseNoteDel.addActionListener(this);
        crseNote.add(crseNoteDel);

        /// Adding he menu bar and the Label to the frame.
        this.setJMenuBar(menuBar);

        //--------------------------------------------------------------------//
        JToolBar toolBar = new JToolBar();
        // Setting up the ButtonBar
        JButton button = null;
        button = makeButton("Create", "NewNote",
                "Create a new note.",
                "New");
        toolBar.add(button);
        button = makeButton("closed door", "Close",
                "Close this note.",
                "Close");
        toolBar.add(button);
        toolBar.addSeparator();
        button = makeButton("exit", "Exit",
                "Exit from this program.",
                "Exit");
        toolBar.add(button);

        add(toolBar, BorderLayout.NORTH);
        //--------------------------------------------------------------------//
        //pnlTop.add(menuBar);
        add(pnlTop, BorderLayout.EAST);
        pnlTop.add(crseCombo, BorderLayout.NORTH);
        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setBorder(BorderFactory.createLineBorder(Color.black));

        txtNewNote.setFont(fnt);
        pnlWest.add(txtNewNote);

        JButton btnAddNote = new JButton("Add note");
        btnAddNote.setActionCommand("NewNote");
        btnAddNote.addActionListener(this);
        pnlWest.add(btnAddNote);

        JButton btnShowText = new JButton("Show note");

        btnShowText.setActionCommand("NewNote");
        btnShowText.addActionListener(this);

        pnlWest.add(btnShowText);
        pnlWest.add(txtShowText);

        /*Adding a button
        pnlWest.add(MyButton);
        MyButton.addActionListener(this);
        MyButton.setSize(50, 40);*/
        add(pnlWest, BorderLayout.WEST);
        pnlWest.setBackground(Color.LIGHT_GRAY);

        /// Center JPanel ///
        JPanel cen = new JPanel();
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setBorder(BorderFactory.createLineBorder(Color.black));
        txtDisplayNotes.setFont(fnt);
        cen.add(txtDisplayNotes);
        add(BorderLayout.CENTER, cen);

        //NO NEED FOR A LABEL (LEFT FOR FUTURE REFERENCE)
        //String html = "<html><body>Hello <b>Andy</b></body></html>";
        //JLabel cenLbl = new JLabel(html);
        //cenLbl.setFont(fnt);
        //cen.add(cenLbl);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Coursework - Andy Wicks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true); // Needed to ensure that the items can be seen.

    }

    private void controller() {
        addAllNotes();
    }

    protected JButton makeButton(
            String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        //Look for the image.
        String imgLocation = System.getProperty("user.dir")
                + "\\icons\\"
                + imageName
                + ".png";

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            // image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button.setIcon(img);
        } else {
            // image NOT found
            button.setText(altText);
            System.err.println("Resource not found: " + imgLocation);
        }

        return button;
    }

    private void addNote(String text) {
        note.add(txtNewNote.getText());
        addAllNotes();
    }

    private void addAllNotes() {
        String allNotes = "";

        for (String n : note) {
            allNotes += n + "\n";
        }

        txtDisplayNotes.setText(allNotes);
    }

    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped not coded yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == 'x') {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased not coded yet.");
    }

    @Override
    public void menuSelected(MenuEvent me) {
        if (me.getSource().equals(exit)) {
            System.exit(0); // Closes the program
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
