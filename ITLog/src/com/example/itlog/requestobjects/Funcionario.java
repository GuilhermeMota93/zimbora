package com.example.itlog.requestobjects;

public class Funcionario {
	String nome, mail;
	int id;
	
	public Funcionario(){
		
	}
	
	public Funcionario(String nome, String mail, int id){
		this.nome = nome;
		this.mail = mail;
		this.id = id;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getMail(){
		return mail;
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public int getID(){
		return id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
}
