package metier;

public class Utilisateur extends Compte {
	
	
	public Utilisateur(String login, String password) {
		super(login, password);
	}

	public Utilisateur(int id,String login, String password) {
		super(id,login, password);
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
	
	
}



