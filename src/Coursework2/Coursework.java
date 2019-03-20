/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coursework2;

/**
 *
 * @author av7521l
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
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

public class Coursework extends JFrame implements MenuListener, ActionListener, KeyListener {

    CommonCode cc = new CommonCode();
    JPanel txtpnl = new JPanel(new BorderLayout());
    JPanel pnl = new JPanel(new BorderLayout());
    JTextField txtShowText = new JTextField(20);
    /// Search Bar --------------
    JTextField search = new JTextField();

    JTextArea txtNewNote = new JTextArea();
    JTextArea txtDisplayNotes = new JTextArea();

    JComboBox crseCombo;

    //JLabel lblText = new JLabel(); Ment for JComboBox?
    String crse = "";
    AllNotes allNotes = new AllNotes();

    ArrayList<String> note = new ArrayList<>();
    ArrayList<String> course = new ArrayList<>();
    /// String for JComboBox
    String[] crList = {"COMP-1752", "COMP-1753",
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
        Coursework prg = new Coursework();
        //Constructors
        CourseworkOverview crsOver = new CourseworkOverview();
        CourseworkItem crsItem = new CourseworkItem();
        

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("Coursework".equals(ae.getActionCommand())) {
            CWDetails cw = new CWDetails();
        }
        if ("Course".equals(ae.getActionCommand())) {
            crse = crseCombo.getSelectedItem().toString();
            System.out.println(crse);
        }
        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
        if ("NewNote".equals(ae.getActionCommand())) {
            addNote(txtNewNote.getText());
            txtNewNote.setText("");
        }
        if ("SearchKeyword".equals(ae.getActionCommand())) {
            String lyst = allNotes.searchAllNotesByKeyword("", 0, search.getText());
            txtDisplayNotes.setText(lyst);
        }

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

    public Coursework() {
        this.crseCombo = new JComboBox(crList);

        model();
        view();
        controller();
    }

    private void model() {

        //crse = course.get(0);
    }

    private void addNote(String text) {
        allNotes.addNote(allNotes.getMaxID(), crse, text);
        addAllNotes();
    }

    private String getDateAndTime() {
        String UK_DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
        String ukDateAndTime;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat uksdf = new SimpleDateFormat(UK_DATE_FORMAT_NOW);
        ukDateAndTime = uksdf.format(cal.getTime());
        return ukDateAndTime;
    }

    private void addAllNotes() {
        String txtNotes = "";
        for (Note n : allNotes.getAllNotes()) {
            txtNotes += n.getNote() + "\n";
        }
        txtDisplayNotes.setText(txtNotes);
    }

    private void view() {
        Font fnt = new Font("Georgia", Font.PLAIN, 24);
        JPanel pnlTop = new JPanel();

        ///---------------------------------------------------------------------
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

        /// Adding another sub-menu to crseMenu.
        crseOp = new JMenu("Course Options");
        crseOp.addMenuListener(this);
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
        crseNoteDel = new JMenuItem("Delete Notes",
                new ImageIcon(""));
        crseNoteDel.addActionListener(this);
        crseNote.add(crseNoteDel);

        /// Adding he menu bar and the Label to the frame.
        this.setJMenuBar(menuBar);

        //--------------------------------------------------------------------//
        // This will add each course to the combobox
        for (String crse : course) {
            crseCombo.addItem(crse);
        }
        crseCombo.setFont(fnt);
        crseCombo.setMaximumSize(crseCombo.getPreferredSize());
        crseCombo.addActionListener(this);
        crseCombo.setActionCommand("Course");
        //menuBar.add(crseCombo);

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
        toolBar.add(Box.createHorizontalGlue());
        //--------------------------------------------------------------------//
        //Search bar -----------------------------------------------------------
        search.setMaximumSize(new Dimension(6900, 50));
        search.setFont(fnt);
        toolBar.add(search);
        toolBar.addSeparator();
        button = makeButton("search", "SearchKeyword",
                "Search for this text.",
                "Search");
        toolBar.add(button);

//----------------------------------------------------------------------------//
        pnlTop.add(menuBar);
        add(pnlTop, BorderLayout.EAST);

        ///Adds the crseCombo to pnlTop-----------------------------------------
        pnlTop.add(crseCombo);
        crseCombo.setSelectedIndex(1);
        crseCombo.addActionListener(this);
        ///---------------------------------------------------------------------
        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setBorder(BorderFactory.createLineBorder(Color.black));

        txtNewNote.setFont(fnt);
        pnlWest.add(txtNewNote);

        //--------------------------------------------------------------------//
        ///Button for adding notes
        JButton btnAddNote = new JButton("Add note");
        btnAddNote.setActionCommand("NewNote");
        btnAddNote.addActionListener(this);
        pnlWest.add(btnAddNote);

        //JButton btnShowText = new JButton("Show note");
        //btnShowText.setActionCommand("NewNote");
        //btnShowText.addActionListener(this);
        //pnlWest.add(btnShowText);
        //pnlWest.add(txtShowText);
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
        /*String html = "Arunas Vinikas Code Master!";
        JLabel cenLbl = new JLabel(html);
        cenLbl.setFont(fnt);
        cen.add(cenLbl);*/
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

    @Override
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

    }

    @Override
    public void menuCanceled(MenuEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
