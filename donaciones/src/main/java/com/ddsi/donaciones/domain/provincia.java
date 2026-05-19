public class Provincia{
    private String nombre;
    private Pais pais;

    public Provincia(String nombre, Pais pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Pais getPais(){
        return this.pais;
    }

    public getUbicacionCompleta(){
        return this.nombre + ", " + this.pais.getNombre();
    }
}