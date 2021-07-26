package metier;

public abstract class Compte {
	protected int id;
	protected String login;
	protected String password;
	
	
	public Compte(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Compte(int id, String login, String password) {
		this.id=id;
		this.login = login;
		this.password = password;
	}
	public Compte() {
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}



