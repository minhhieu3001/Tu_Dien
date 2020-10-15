package ALL.Model;

import ALL.MainApp;

import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DictionaryManagement {
    public static String file_url = "src/ALL/Dictionary.txt";

    public static void insertFromCommandline(Dictionary dict) {
        System.out.print("Nhap so tu muon them: ");
        Scanner sc = new Scanner(System.in);
        int wordCount = sc.nextInt();
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for(int i=0; i< wordCount; i++) {
            System.out.println("STT: " + (i+1));
            System.out.print("Nhap tu tieng anh: ");
            String english = sc.nextLine();
            System.out.print("Nhap tu tieng viet: ");
            String vietnam = sc.nextLine();
            Word newWord = new Word(english, vietnam);
            dict.addNewWord(newWord);
        }
    }

    public static void insertFromFile(Dictionary dict) throws IOException {
        File file = new File(file_url);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = "";
        while((line = reader.readLine()) != null) {
            int tab = line.indexOf(9);
            String word = line.substring(0, tab);
            String explain = line.substring(tab + 1);
            Word newWord = new Word(word, explain);
            dict.addNewWord(newWord);
        }
    }

    public static void dictionaryLookup(Dictionary dict) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu muon tim: ");
        String findWord = sc.nextLine();
        String result = Dictionary.dictionaryLookup(findWord);
        if(result.equals("Khong co tu nay trong tu dien")) {
            System.out.println(result);
        } else {
            System.out.print("Nghia cua tu muon tim la: ");
            System.out.println(result);
        }
    }

    public static void readDatabase(Dictionary dict) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Project","root","hungnguyen");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from project.dictionary order by word");

            while(rs.next()) {
                Word w = new Word();
                w.setWord_target(rs.getString(1));
                w.setWord_explain(rs.getString(2));
                dict.addNewWord(w);
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static void add(Dictionary dict) {
        System.out.print("Tu ma ban muon them la: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        System.out.print("Nghia cua tu ban vua them la: ");
        String explain = sc.nextLine();
        Word newWord = new Word(word, explain);
        dict.addNewWord(newWord);
    }

    public static void remove(Dictionary dict) {
        System.out.print("Tu ma ban muon bo la: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        dict.removeWord(word);
    }

    public static void addToDatabase(String addedWord, String addedMean) {
        try{
            addedMean = "<C><F><I><N><Q>@" + addedWord + "<br />-" + addedMean + "</Q></N></I></F></C>";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project","root","hungnguyen");

            Statement stmt=con.createStatement();
            stmt.executeUpdate("insert into project.dictionary value (" +
                    "'" + addedWord + "'" + "," + "'" + addedMean + "'" + ")"  );
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static void removeFromDatabase(String removedWord) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project","root","hungnguyen");

            Statement stmt=con.createStatement();
            stmt.executeUpdate("delete from project.dictionary where word = " +
                    "\"" + removedWord + "\"");
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    public static void changeFromDatabase(String cWord, String vWord) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project","root","hungnguyen");

            Statement stmt=con.createStatement();
            stmt.executeUpdate("update project.dictionary set mean =" + "'" + vWord + "'" +
                    "where word = " + "'" + cWord + "'");
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static void dictionarySearch(Dictionary dict) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap cac ki tu: ");
        String findChar = sc.nextLine();
        System.out.println("Cac tu lien quan la: ");
        dict.dictionarySearcher(findChar);
    }

    public static void dictionaryExportFile(Dictionary dict) {
        dict.ExportFile();
    }



}