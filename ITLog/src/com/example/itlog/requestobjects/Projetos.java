package com.example.itlog.requestobjects;

public class Projetos {
	String nome, gestor, descricao;
	int id, horas;

	public Projetos() {

	}

	public Projetos(String nome, String gestor, String descricao, int id,
			int horas) {
		this.nome = nome;
		this.gestor = gestor;
		this.descricao = descricao;
		this.id = id;
		this.horas = horas;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getHoras(){
		return horas;
	}
	
	public void setHoras(int horas){
		this.horas = horas;
	}
	
	
}
