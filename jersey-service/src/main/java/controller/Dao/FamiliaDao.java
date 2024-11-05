package controller.Dao;

import models.Familia;

import controller.Dao.implement.AdapterDao;
import controller.tda.list.LinkedList;

public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia = new Familia(); 
    private LinkedList listAll;
    
    public FamiliaDao(){
        super(Familia.class);
    }
    public Familia getFamilia(){ 
        if (familia == null) {
            familia = new Familia(); 
        }
        return this.familia; 
    }

    public void setFamilia(Familia familia){ 
        this.familia = familia; 
    }

    public LinkedList getlistAll(){ 
        if (listAll == null) { 
            this.listAll = listAll();
        }
        return listAll; 
    }
    public Boolean save() throws Exception{ 
        Integer id = getlistAll().getSize()+1; 
        familia.setId(id); 
        this.persist(this.familia);
        this.listAll = listAll(); 
        return true;
    }

    public Boolean update() throws Exception{ 
        this.merge(getFamilia(), getFamilia().getId() -1); 
        this.listAll = listAll(); 
        return true; 
    }

    public Boolean delete(int index) throws Exception { 
        this.supreme(index);
        this.listAll = listAll(); 
        return true; 
    }
    
    public int contarFamiliasConGenerador() {
        int contador = 0;
        LinkedList<Familia> familias = listAll(); 
        Familia[] familiasArray = familias.toArray();

        for (Familia familia : familiasArray) { 
            if (familia.getTieneGenerador()) { 
                contador++;
            }
        }
        return contador;
    }

}