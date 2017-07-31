package com.gamaliel.surya;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LogParser")
public class LogParser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
	public String match = "New";
	public String myFile = "E:\\test.log";
       
    public LogParser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String result = readFile();
		
		String temp = "";
		Integer hit = 0;
		
		Integer myIndex = result.indexOf(match);
		while (myIndex >= 0 ) {
			hit = result.indexOf(match);
			temp = temp + result.substring(0, hit) + "<span style=\"background-color:#0FF;\">" + match + "</span>";
			result = result.substring(hit+match.length());
			myIndex = result.indexOf(match);
		}
		
		temp = temp + result;
		
		out.println(HTML_START + temp +HTML_END);
	}
	
	private String readFile() throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader(myFile));
		
		StringBuilder sb = new StringBuilder();
		while(in.hasNext()) {
			sb.append(in.next());
		}
		in.close();
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
