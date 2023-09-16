package it.polito.tdp.exam.model;

import java.util.Objects;


public class Successore implements Comparable<Successore>{
	
	private Integer e;
	private Integer peso;
	public Successore(Integer e, Integer peso) {
		super();
		this.e = e;
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Successore [e=" + e + ", peso=" + peso + "]";
	}
	public Integer getE() {
		return e;
	}
	public void setE(Integer e) {
		this.e = e;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(e, peso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Successore other = (Successore) obj;
		return Objects.equals(e, other.e) && Objects.equals(peso, other.peso);
	}
	@Override
	public int compareTo(Successore o) {
		return o.getPeso()-this.peso;
	}
	
	
	
	
}
