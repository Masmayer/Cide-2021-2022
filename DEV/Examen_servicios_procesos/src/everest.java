import java.util.concurrent.Semaphore;
import java.util.PriorityQueue;

class Cima {
    private Semaphore semaforo;
    private PriorityQueue <Integer> listaEscaladores;


    public Cima() {
        semaforo = new Semaphore(1);
        listaEscaladores = new PriorityQueue <Integer>();
    }

    public void nuevoEscalador() {
        try {
            semaforo.acquire();
            listaEscaladores.add(1);
            semaforo.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized int pasajerosRestantes(){
        return this.listaEscaladores.size();
    }

    public void recogerEscalador(Helicoptero helicoptero) throws Exception{
        if (isEscaladoresPendientes()) {
            semaforo.acquire();
            for (int i = 0; i < helicoptero.getCapacidad(); i++) {
                Integer comprobacion = listaEscaladores.poll();
                if (comprobacion == null){
                    throw new Exception("No hay mas escaladores");

                }
                helicoptero.incrementarPasajero();
            }
            System.out.println("-----------------------------------\nHelicoptero: " + helicoptero.getIdentif() + "\nCapacidad de escaladores: " + helicoptero.getCapacidad() + "\nEscaladores por salvar: " + this.pasajerosRestantes() + "\n-----------------------------------");
            //System.out.println("Helicoptero " + helicoptero.getIdentif() + " esta recogiendo escaladores....");
            semaforo.release();
        }
    }

    public synchronized boolean isEscaladoresPendientes() {
        return listaEscaladores.size() > 0;
    }

}

class Helicoptero extends Thread {
    private int identif;
    private Cima cima;
    private int capacidad;
    private int pasajeros;

    public Helicoptero(int identif, Cima cima, int capacidad) {
        this.identif = identif;
        this.cima = cima;
        this.capacidad = capacidad;

    }

    public int getIdentif() {
        return identif;
    }
    public int getCapacidad() {
        return capacidad;
    }

    public synchronized void incrementarPasajero(){
        this.pasajeros++;
    }
    public void run() {
        while (cima.isEscaladoresPendientes()) {
            try {
                System.out.println("Helicoptero " + this.identif + " en espera.");
                cima.recogerEscalador(this);
                sleep((long) (1000));
                // sleep((long) (Math.random() * 20000 + 15000));
                this.pasajeros = 0;
                //System.out.println("Helicoptero " + this.identif + " ha recogido a " + this.capacidad + " escaladores, quedan " + cima.pasajerosRestantes() + " por salvar");
            } catch (Exception e) {
                System.out.println("El Helicoptero " + this.identif + " tenia " + this.capacidad + " de capacidad y ha recogido a " + this.pasajeros + "\nEscaladores salvados!!");

                e.getMessage();
            }

        }
    }
}

class Escalador extends Thread {
    private Cima cima;

    public Escalador(Cima itv) {
        this.cima = itv;
    }

    public void run() {
        cima.nuevoEscalador();
    }
}

public class everest {

    public static void main(String[] args) {
        Cima cima = new Cima();

        // Creaci??n de veh??culos
        Escalador[] v = new Escalador[86];
        for (int i = 0; i < 86; i++) {
            v[i] = new Escalador(cima);
            v[i].start();
        }
        // Creaci??n de puestos
        Helicoptero[] p = new Helicoptero[3];
        p[0] = new Helicoptero(0, cima, 5);
        p[1] = new Helicoptero(1, cima, 3);
        p[2] = new Helicoptero(2, cima, 1);

        for (int i = 0; i < p.length; i++) {
            p[i].start();
        }

        // Se espera a que terminen todos los puestos
        for (int i = 0; i < p.length; i++) {
            try {
                p[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Se espera a que terminen todos veh??culos
        for (int i = 0; i < v.length; i++) {
            try {
                v[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Escaladores salvados, viva!");
    }
}
