
public class Proceso {
	private int tiempoLlegada;
	private int tiempoEjecucion;
	private int inicio;
	private int fin;
	private int T;
	private int espera;
	private double penalizacion;

	public Proceso() {

	}

	public Proceso(int tiempoLlegada, int tiempoEjecucion, int inicio, int fin, int T, int espera,
			double penalizacion) {
		this.tiempoLlegada = tiempoLlegada;
		this.tiempoEjecucion = tiempoEjecucion;
		this.inicio = inicio;
		this.fin = fin;
		this.T = T;
		this.espera = espera;
		this.penalizacion = penalizacion;
	}

	public int getTiempoLlegada() {
		return tiempoLlegada;
	}

	public void setTiempoLlegada(int tiempoLlegada) {
		this.tiempoLlegada = tiempoLlegada;
	}

	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(int tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getT() {
		return T;
	}

	public void setT(int T) {
		this.T = T;
	}

	public int getEspera() {
		return espera;
	}

	public void setEspera(int espera) {
		this.espera = espera;
	}

	public double getPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(double penalizacion) {
		this.penalizacion = penalizacion;
	}

	@Override
	public String toString() {
		return tiempoLlegada + "\t" + tiempoEjecucion + "\t\t" + inicio + "\t" + fin + "\t" + T + "\t" + espera + "\t"
				+ penalizacion;
	}

}
