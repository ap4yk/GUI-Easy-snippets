/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coursework2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author av7521l
 */
public class AllNotes extends CommonCode {
    

    ArrayList<Note> allNotes = new ArrayList<>();
    private String crse = "";
    private int maxID = 0;

    AllNotes() {
        readAllNotes();
    }

    public final int getMaxID() {
        maxID++;
        return maxID;
    }

    private void readAllNotes() {
        ArrayList<String> readNotes = new ArrayList<>();

        readNotes = readTextFile(appDir + fileSeparator + "\\Notes.txt");
        //System.out.println(readNotes.get(0));

        if (!"File not found".equals(readNotes.get(0))) {
            allNotes.clear();
            for (String str : readNotes) {
                String[] tmp = str.split("\t");
                System.out.println(str);
                
                Note n = new Note();
                n.setNoteID(Integer.parseInt(tmp[0]));
                n.setCourse(tmp[1]);
                n.setDayte(tmp[2]);
                n.setNote(tmp[3]);
                
                allNotes.add(n);

                /*int nid = Integer.parseInt(tmp[0]);
                Note n = new Note(nid, tmp[1], tmp[2], tmp[3]);
                allNotes.add(n);

                if (nid > maxID) {
                    maxID = nid;
                }*/
                
            }
        }
        maxID++;
    }

    public void addNote(int maxID, String course, String note) {
        Note myNote = new Note(maxID, course, note);
        allNotes.add(myNote);
        writeAllNotes();
    }

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    private void writeAllNotes() {
        String path = appDir + fileSeparator + "\\Notes.txt";
        ArrayList<String> writeNote = new ArrayList<>();

        allNotes.stream().map((n) -> {
            String tmp = n.getNoteID() + "\t";
            tmp += n.getCourse() + "\t";
            tmp += n.getDayte() + "\t";
            tmp += n.getNote();
            return tmp;
        }).forEachOrdered((tmp) -> {
            writeNote.add(tmp);
        });
        try {
            //FileWriter writer = new FileWriter("path");
            //BufferedWriter bw = new BufferedWriter(writer);
            writeTextFile(path, writeNote);
        } catch (IOException e) {
            System.out.println("Problem! " + path);
        }
    }

    public String searchAllNotesByKeyword(String noteList, int i, String s) {
        if (i == allNotes.size()) {
            return noteList;
        }

        if (allNotes.get(i).getNote().contains(s)) {
            noteList += allNotes.get(i).getNote() + "\n";
        }

        return searchAllNotesByKeyword(noteList, i + 1, s);
    }

}
