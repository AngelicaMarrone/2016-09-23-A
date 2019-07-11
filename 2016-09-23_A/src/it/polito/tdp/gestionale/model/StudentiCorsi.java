package it.polito.tdp.gestionale.model;

public class StudentiCorsi {
	
	private int numStudenti;
	private int numCorsi;
	public StudentiCorsi(int numStudenti, int numCorsi) {
		super();
		this.numStudenti = numStudenti;
		this.numCorsi = numCorsi;
	}
	public int getNumStudenti() {
		return numStudenti;
	}
	public void setNumStudenti(int numStudenti) {
		this.numStudenti = numStudenti;
	}
	public int getNumCorsi() {
		return numCorsi;
	}
	public void setNumCorsi(int numCorsi) {
		this.numCorsi = numCorsi;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numCorsi;
		result = prime * result + numStudenti;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentiCorsi other = (StudentiCorsi) obj;
		if (numCorsi != other.numCorsi)
			return false;
		if (numStudenti != other.numStudenti)
			return false;
		return true;
	}
	

}
