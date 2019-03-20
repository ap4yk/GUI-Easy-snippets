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
public class Note extends CommonCode {

    private int noteID = 0;
    private String course = "";
    private String dayte = "";
    private String note = "";

    public Note() {
        storeThisNote();

    }

    Note(int nid, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Note(int maxID, String course, String note) {
    
    }

    public void setNoteID(int n) {
        int nid = n;
        // Any validation goes here.
        noteID = nid;
    }

    public void setCourse(String c) {
        String crse = c;
        // Any validation goes here.
        course = crse;
    }

    public int getNoteID() {
        // Any checking goes here.
        return noteID;
    }

    public String getCourse() {
        // Any checking goes here.
        return course;
    }

    public void setDayte() {
        dayte = orderedDate;
    }

    public void setDayte(String d) {
        dayte = d;
    }

    public String getDayte() {
        return dayte;
    }

    public void setNote(String n) {
        // Any validation goes here.
        note = n;
    }

    public String getNote() {
        // Any checking goes here.
        return note;
    }

    private void storeThisNote() {
   
    }
}
