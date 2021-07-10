package br.cefet.serial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 *
 * @author User
 */
public class SerialRxTx implements SerialPortEventListener{

    SerialPort serialPort = null;
    
    private Protocolo protocolo = new Protocolo();
    private String appName;
    
    private BufferedReader input;
    private OutputStream output;
    
    private static final int TIME_OUT = 1000;
    private static final int DATA_RATE = 9600;

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private String serialPortName = "COM5";

    public boolean iniciaSerial(){
        boolean status;
        try{
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
            
            while (portEnum.hasMoreElements()){
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                
                if (currPortId.getName().equals(serialPortName) || currPortId.getName().startsWith(serialPortName)){
                    serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
                    portId = currPortId;
                    System.out.println("Conectado em: " + currPortId.getName());
                    break;
                }
            }
            
            if (portId == null || serialPort == null){
                return false;
            }
            
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8, 
                    SerialPort.STOPBITS_1, 
                    SerialPort.PARITY_NONE);
            
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            status = true;
            
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        
        return status;
        
    }
    
    public void sendData (String data){
        try{
            output = serialPort.getOutputStream();
            output.write(data.getBytes());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    public synchronized void close(){
        if (serialPort != null){
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
    
    /**
     *
     * @param spe
     */
    public void serialEvent(SerialPortEvent spe) {
        //método que lida com a chegada de dados pela serial
        try{
            switch(spe.getEventType()){
                case SerialPortEvent.DATA_AVAILABLE:
                    if (input == null){
                        input = new BufferedReader(
                                new InputStreamReader(
                                        serialPort.getInputStream()));
                    }
                    
                    if (input.ready()){
                        protocolo.setLeituraComando(input.readLine());
                        final String leituraComando = protocolo.getLeituraComando();
                        System.out.println(leituraComando);

                        //TODO: Comando das setas
                        if(leituraComando.equals("X")){
                            acionaSeta(LEFT);
                        }

                        if(leituraComando.equals("Y")){
                            acionaSeta(RIGHT);
                        }

                    }

                    break;
                    
                default:
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void acionaSeta(int seta) {
        if(seta == LEFT){
            //aciona seta
        }
        if(seta == RIGHT){
            //aciona seta
        }
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public String getSerialPortName() {
        return serialPortName;
    }

    public void setSerialPortName(String serialPortName) {
        this.serialPortName = serialPortName;
    }
    
    
    
}
