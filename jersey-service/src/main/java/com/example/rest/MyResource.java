package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import controller.Dao.servicies.FamiliaServicies;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap<String, Object> mapa = new HashMap<>();
        FamiliaServicies fs = new FamiliaServicies();
        String aux = "";

        try {
            // Crear y guardar primera familia
            fs.getFamilia().setId(1);
            fs.getFamilia().setCanton("Loja");
            fs.getFamilia().setApellidoPaterno("Sarango");
            fs.getFamilia().setApellidoMaterno("Tandazo");
            fs.getFamilia().setIntegrantes(4);
            fs.getFamilia().setTieneGenerador(true);
            fs.save();

            // Crear y guardar segunda familia
            fs.getFamilia().setId(2);
            fs.getFamilia().setCanton("Cartago");
            fs.getFamilia().setApellidoPaterno("Rodríguez");
            fs.getFamilia().setApellidoMaterno("Jiménez");
            fs.getFamilia().setIntegrantes(3);
            fs.getFamilia().setTieneGenerador(false);
            fs.save();

            aux = "La lista está vacía: " + fs.listAll().isEmpty();
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e);
        }

        mapa.put("msg", "Ok");
        mapa.put("data", aux);

        // Construir la respuesta correctamente
        return Response.ok(mapa).build();
    }
}