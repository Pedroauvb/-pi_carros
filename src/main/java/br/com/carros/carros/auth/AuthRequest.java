package br.com.carros.carros.auth;

public class AuthRequest {
	private String email;
	private String Senha;

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
