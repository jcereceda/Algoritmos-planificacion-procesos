import java.util.Map.Entry;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Planificador {
	static TreeMap<Integer, Proceso> planificador = new TreeMap<>();
	static Scanner in = new Scanner(System.in);
	static int tiempoLlegada, tiempoEjecucion, inicio, fin, T, espera, suma;
	static double penalizacion;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int opcion;
		do {
			tiempoLlegada = 0;
			tiempoEjecucion = 0;
			inicio = 0;
			fin = 0;
			T = 0;
			espera = 0;
			suma = 0;
			penalizacion = 0;
			planificador.clear();
			System.out.println("========================================");
			System.out.println("|PROGRAMA DE PLANIFICACION DE ALGORIMOS|");
			System.out.println("========================================");
			System.out.println("|1 - Algoritmo FCFS                    |");
			System.out.println("|2 - Algoritmo SJF                     |");
			System.out.println("|3 - Algoritmo Round Robin             |");
			System.out.println("|0 - SALIR                             |");
			System.out.println("========================================");
			System.out.print("\nOpcion: ");
			opcion = in.nextInt();

			switch (opcion) {
			case 1:
				FCFS();
				break;
			case 2:
				SJF();
				break;
			case 3:
				RR();
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} while (opcion != 0);

	}

	private static void RR() throws NumberFormatException, IOException {
		System.out.println("\n========================================");
		System.out.println("==============ROUND ROBIN===============");
		System.out.println("========================================\n");
		DataInputStream in = new DataInputStream(System.in);
		int i, j, k, q, sum = 0;
		System.out.print("Introduce numero de procesos: ");
		int n = Integer.parseInt(in.readLine());
		int bt[] = new int[n];
		int wt[] = new int[n];
		int tat[] = new int[n];
		int a[] = new int[n];
		for (i = 0; i < n; i++) {
			System.out.print("Introduce tiempo de ejecucion para proceso " + (i + 1) + ": ");
			bt[i] = Integer.parseInt(in.readLine());
		}
		System.out.print("Introduce el quantum: ");
		q = Integer.parseInt(in.readLine());
		for (i = 0; i < n; i++)
			a[i] = bt[i];
		for (i = 0; i < n; i++)
			wt[i] = 0;
		do {
			for (i = 0; i < n; i++) {
				if (bt[i] > q) {
					bt[i] -= q;
					for (j = 0; j < n; j++) {
						if ((j != i) && (bt[j] != 0))
							wt[j] += q;
					}
				} else {
					for (j = 0; j < n; j++) {
						if ((j != i) && (bt[j] != 0))
							wt[j] += bt[i];
					}
					bt[i] = 0;
				}
			}
			sum = 0;
			for (k = 0; k < n; k++)
				sum = sum + bt[k];
		} while (sum != 0);
		for (i = 0; i < n; i++)
			tat[i] = wt[i] + a[i];
		System.out.println("Proceso\t\tTiempo Ejecucion\tTiempo espera\tTotal");
		for (i = 0; i < n; i++) {
			System.out.println((i + 1) + "\t\t" + a[i] + "\t\t\t" + wt[i] + "\t\t" + tat[i]);
		}
	}

	private static void SJF() {
		System.out.println("\n========================================");
		System.out.println("==========Shortest Job First============");
		System.out.println("========================================\n");
		System.out.print("Cuantos procesos vas a introducir: ");
		int cant = in.nextInt();
		int ID[] = new int[cant];
		int tiempoLlegada[] = new int[cant];
		int tiempoEjecucion[] = new int[cant];
		int fin[] = new int[cant];
		int T[] = new int[cant];
		int espera[] = new int[cant];
		int completado[] = new int[cant];
		int inicio[] = new int[cant];
		double penalizacion[] = new double[cant];
		int suma = 0, tot = 0;
		for (int i = 0; i < cant; i++) {
			System.out.print("Tiempo de llegada del proceso " + (i + 1) + ":");
			tiempoLlegada[i] = in.nextInt();
			System.out.print("Tiempo de ejecucion del proceso " + (i + 1) + " :");
			tiempoEjecucion[i] = in.nextInt();
			ID[i] = i + 1;
			completado[i] = 0;
		}
		boolean a = true;
		while (true) {
			int c = cant, min = 999;
			if (tot == cant)
				break;
			for (int i = 0; i < cant; i++) {
				if ((tiempoLlegada[i] <= suma) && (completado[i] == 0) && (tiempoEjecucion[i] < min)) {
					min = tiempoEjecucion[i];
					c = i;
				}
			}

			if (c == cant)
				suma++;
			else {
				fin[c] = suma + tiempoEjecucion[c];
				suma += tiempoEjecucion[c];
				T[c] = fin[c] - tiempoLlegada[c];
				espera[c] = T[c] - tiempoEjecucion[c];
				completado[c] = 1;
				tot++;
				inicio[c] = suma - tiempoEjecucion[c];
				penalizacion[c] = (double) T[c] / tiempoEjecucion[c];
			}
			for (int i = 0; i < cant; i++) {
				Proceso proc = new Proceso(tiempoLlegada[i], tiempoEjecucion[i], inicio[i], fin[i], T[i], espera[i],
						penalizacion[i]);
				planificador.put(ID[i], proc);
			}
		}
		show();

	}

	private static void FCFS() {
		System.out.println("\n========================================");
		System.out.println("==========First In First Out============");
		System.out.println("========================================\n");
		System.out.print("Cuantos procesos vas a introducir: ");
		int cant = in.nextInt();
		for (int i = 1; i <= cant; i++) {
			System.out.print("Tiempo de llegada proceso " + i + ": ");
			tiempoLlegada = in.nextInt();
			System.out.print("Tiempo de ejecucion del proceso " + i + ": ");
			tiempoEjecucion = in.nextInt();
			suma += tiempoEjecucion;
			inicio = suma - tiempoEjecucion;
			fin = suma;
			T = fin - tiempoLlegada;
			espera = T - tiempoEjecucion;
			penalizacion = (double) T / tiempoEjecucion;
			Proceso proc = new Proceso(tiempoLlegada, tiempoEjecucion, inicio, fin, T, espera, penalizacion);
			planificador.put(planificador.size() + 1, proc);
		}
		show();
	}

	private static void show() {
		System.out.println("\nPROCESO\tLlegada\tEjecucion\tInicio\tFin\tTotal\tEspera\tPenalización ");
		for (Entry<Integer, Proceso> entry : planificador.entrySet()) {
			Integer key = entry.getKey();
			Proceso val = entry.getValue();
			System.out.println(key + "\t" + val);
		}
		System.out.println("\nREPRESENTACIÓN\n");
		for (Entry<Integer, Proceso> entry : planificador.entrySet()) {
			Integer key = entry.getKey();
			Proceso val = entry.getValue();
			System.out.print(key + " ");
			for (int i = 0; i < val.getTiempoLlegada(); i++) {
				System.out.print("   ");
			}
			for (int i = 0; i < val.getEspera(); i++) {
				System.out.print("-  ");
			}
			for (int i = 0; i < val.getTiempoEjecucion(); i++) {
				System.out.print("#  ");
			}
			System.out.println("");
		}

	}
}
