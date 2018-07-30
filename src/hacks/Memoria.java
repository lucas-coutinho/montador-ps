

package hacks;

/**
 *
 * @author william
 */
public class Memoria {
    
    private Integer[] memoriaDados = new Integer[32768]; //MemÃ³ria de Dados
    private String[] memoriaInstrucao = new String[32768]; //MemÃ³ria de InstruÃ§Ãµes
    private int registradorA;
    private int registradorD;
    private int PC; //PC sempre vai apontar para a posiÃ§Ã£o da MemÃ³ria de InstruÃ§Ãµes (ROM)
    

    public Memoria() {
        this.zeraMemoriaDados();
        this.zeraMemoriaInstrucao();
    }
    
    private void zeraMemoriaDados(){
        for(int i = 0; i < 32768; i++){
            this.memoriaDados[i] = 0;
        }
    }
    
    private void zeraMemoriaInstrucao(){
        for(int i = 0; i < 32768; i++){
            this.memoriaInstrucao[i] = null;
        }
    }

    
    
    
    //MEMORIA    
    public int getMemoriaDados() {
        return memoriaDados[this.getRegistradorA()];
    }

    public void setMemoriaDados(int memoriaDados) {
        this.memoriaDados[this.getRegistradorA()] = memoriaDados;
    }

    public String getMemoriaInstrucao() {
        return memoriaInstrucao[this.PC];
    }

   public void setMemoriaInstrucao(String memoriaInstrucao) {
        this.memoriaInstrucao[this.PC] = memoriaInstrucao;
        this.setPC(this.getPC()+1);
    }

    
    
    //REGISTRADORES
    public int getRegistradorA() {
        return registradorA;
    }

    public void setRegistradorA(int registradorA) {
        this.registradorA = registradorA;
    }

    public int getRegistradorD() {
        return registradorD;
    }

    public void setRegistradorD(int registradorD) {
        this.registradorD = registradorD;
    }
    
    //GET & SET PC
    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }
    
    
    
     
     
    
}
