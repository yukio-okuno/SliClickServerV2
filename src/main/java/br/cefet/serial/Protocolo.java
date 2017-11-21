package br.cefet.serial;



/**
 *
 * @author User
 */
class Protocolo {
    private String tipoDado;
    private String avancar;
    private String voltar;
    private String gsr;
    private String batimentos;
 
    private String leituraComando;
 
    
    private void interpretaComando(){
        String aux[] = leituraComando.split(",");
        if (aux.length == 5){
            tipoDado = aux[0];
            avancar = aux[1];
            voltar = aux[2];
            gsr = aux[3];
            batimentos = aux[4];
            
        }
    }

    public String getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(String tipoDado) {
        this.tipoDado = tipoDado;
    }

    public String getAvancar() {
        return avancar;
    }

    public void setAvancar(String avancar) {
        this.avancar = avancar;
    }

    public String getVoltar() {
        return voltar;
    }

    public void setVoltar(String voltar) {
        this.voltar = voltar;
    }

    public String getGsr() {
        return gsr;
    }

    public void setGsr(String gsr) {
        this.gsr = gsr;
    }

    public String getBatimentos() {
        return batimentos;
    }

    public void setBatimentos(String batimentos) {
        this.batimentos = batimentos;
    }


    
    public String getLeituraComando() {
        return leituraComando;
    }

    public void setLeituraComando(String leituraComando) {
        this.leituraComando = leituraComando;
        this.interpretaComando();
    }
}
