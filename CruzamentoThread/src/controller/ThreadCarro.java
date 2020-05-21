package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread{
	private int IDCar;
	private String Partida;
	private String Sa�da;
	private static int posChegada;
	private static int posSaida;
	private Semaphore semaforo;
	
	public ThreadCarro(int IDCar, Semaphore semaforo ) {
		this.IDCar = IDCar;
		this.semaforo = semaforo;	
	}
	
	private void carroAndando() {
		int distanciaTotal = (int)((Math.random() * 1001) + 500);
		int NCardinal = (int)((Math.random()*4) + 1);
		int distanciaPercorrida = 0;
		int deslocamento = 100;
		int tempo = 30;
		if (NCardinal == 1) {
			 Partida = "Norte";
			 Sa�da = "Sul";
		}
		if (NCardinal == 2) {
			 Partida = "Leste";
			 Sa�da = "Oeste";
		}
		if (NCardinal == 3) {
			 Partida = "Sul";
			 Sa�da = "Norte";
		}
		if (NCardinal == 4) {
			 Partida = "Oeste";
			 Sa�da = "Leste";
		}
		System.out.println("Carro #" + IDCar + " entrou pelo " + Partida);
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Carro #" + IDCar + " j� percorreu " + distanciaPercorrida + "m. ");
		}
		posChegada++;
		System.out.println("Carro #" + IDCar + " foi o " + posChegada + "� a chegar. ");
	}
	
	private void carroEstacionado() {
		System.out.println("Carro #" + IDCar + " entrou no cruzamento. ");
		int tempo = (int)((Math.random() * 201) + 100);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Carro #" + IDCar + " saiu do cruzamento. ");
		}
	}
	
	private void carroSaindo () {
		int distanciaTotal = (int)((Math.random() * 1001) + 500);
		int distanciaPercorrida = 0;
		int deslocamento = 100;
		int tempo = 30;
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Carro #" + IDCar + " j� percorreu " + distanciaPercorrida + "m. ");
		}
	 posSaida++;
	 System.out.println("O Carro#" + IDCar + " foi o " + posSaida + "� a sair e foi para o " + Sa�da + ". ");
	}
	@Override		
	public void run() {
		carroAndando();
		try {
			semaforo.acquire();
			carroEstacionado();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			carroSaindo();
		}
	}	
}

