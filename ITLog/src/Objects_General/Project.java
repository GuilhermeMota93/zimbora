package Objects_General;

public class Project {
	String name, manager, descripiton, company;
	int projectid, hours;

	public Project() {

	}

	public Project(String nome, int id, String clienInfo, String gestor,
			String descricao, int horas) {
		this.name = nome;
		this.projectid = id;
		this.company = company;
		this.manager = gestor;
		this.descripiton = descricao;
		this.hours = horas;

	}

	public Project(String name, int projectid, String company, String manager,
			String description) {
		this.name = name;
		this.projectid = projectid;
		this.company = company;
		this.manager = manager;
		this.descripiton = description;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public String getGestor() {
		return manager;
	}

	public void setGestor(String manager) {
		this.manager = manager;
	}

	public String getDescricao() {
		return descripiton;
	}

	public void setDescricao(String description) {
		this.descripiton = description;
	}

	public String getClientInfo() {
		return company;
	}

	public void setClientInfo(String company) {
		this.company = company;
	}

	public int getId() {
		return projectid;
	}

	public void setId(int projectid) {
		this.projectid = projectid;
	}

	public int getHoras() {
		return hours;
	}

	public void setHoras(int hours) {
		this.hours = hours;
	}

}