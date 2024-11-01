package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria() {
        this.desenvolvedores = new ArrayList<>();
    }

    public Consultoria(String nome, Integer vagas, List<Desenvolvedor> desenvolvedores) {
        this.nome = nome;
        this.vagas = vagas;
        this.desenvolvedores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public void contratar(Desenvolvedor dev){
        if(dev != null && vagas > desenvolvedores.size()){
            desenvolvedores.add(dev);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb dev){
        if(dev.isFullstack()){
            contratar(dev);
        }
    }

    public Double getTotalSalarios(){
        Double totalSalarios = 0.0;
        for (int i = 0; i < desenvolvedores.size(); i++) {
            totalSalarios += desenvolvedores.get(i).calcularSalario();
        }
        return totalSalarios;
    }

    public Integer qtdDesenvolvedoresMobile(){
        Integer qtdDevMob = 0;
        for (Desenvolvedor devAtual : desenvolvedores){
            if(devAtual instanceof DesenvolvedorMobile){
                qtdDevMob++;
            }
        }
        return qtdDevMob;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario){
        List<Desenvolvedor> salarioMIQ = new ArrayList<>();
        for (int i = 0; i < desenvolvedores.size(); i++) {
            if(desenvolvedores.get(i).calcularSalario() >= salario){
                salarioMIQ.add(desenvolvedores.get(i));
            }
        }
        return salarioMIQ;
    }

    public Desenvolvedor buscarMenorSalario(){
        //tentando aprender .stream()
        if(desenvolvedores != null && !desenvolvedores.isEmpty()){
            return this.desenvolvedores.stream()
                    .min(Comparator.comparingDouble(Desenvolvedor::calcularSalario))
                    .get();
        }
        return null;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia){
        List<Desenvolvedor> devBuscado = new ArrayList<>();
        for (Desenvolvedor devAtual : desenvolvedores) {
            if(devAtual instanceof DesenvolvedorWeb){
                if( ((DesenvolvedorWeb) devAtual).getBackend().equals(tecnologia) ||
                    ((DesenvolvedorWeb) devAtual).getFrontend().equals(tecnologia) ||
                    ((DesenvolvedorWeb) devAtual).getSgbd().equals(tecnologia)) {

                    devBuscado.add(devAtual);

                }
            } else if(devAtual instanceof DesenvolvedorMobile){
                if( ((DesenvolvedorMobile) devAtual).getPlataforma().equals(tecnologia) ||
                    ((DesenvolvedorMobile) devAtual).getLinguagem().equals(tecnologia)) {

                    devBuscado.add(devAtual);

                }
            }
        }
        return devBuscado;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia){
        Double totalSalario = 0.0;
        for(Desenvolvedor devAtual : desenvolvedores){
            if(devAtual instanceof DesenvolvedorWeb){
                if(((DesenvolvedorWeb) devAtual).getBackend().equals(tecnologia)){
                    totalSalario += devAtual.calcularSalario();
                }
                else if(((DesenvolvedorWeb) devAtual).getSgbd().equals(tecnologia)){
                    totalSalario += devAtual.calcularSalario();
                }
                else if(((DesenvolvedorWeb) devAtual).getFrontend().equals(tecnologia)){
                    totalSalario += devAtual.calcularSalario();
                }
            }
            else if(devAtual instanceof DesenvolvedorMobile){
                if(((DesenvolvedorMobile) devAtual).getLinguagem().equals(tecnologia)){
                    totalSalario += devAtual.calcularSalario();
                }
                else if(((DesenvolvedorMobile) devAtual).getPlataforma().equals(tecnologia)){
                    totalSalario += devAtual.calcularSalario();
                }
            }
        }
        return totalSalario;
    }

}
