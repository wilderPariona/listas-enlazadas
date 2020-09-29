/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listas;

public class ListaSimple<Tipo> {
    NodoSimple primero;
    private int tamanio = 0;

    public ListaSimple() {
        this.primero = null; /*Lista vacia*/
    }

    /* -------------------------------------Esta vacio---------------------------------------------------------------*/
    public boolean esVacio(){
        return primero==null;
    }

    /*------------------------------------- insertar por el primero------------------------------------------------- */
    public ListaSimple insertarPrimero(Tipo dato){
        NodoSimple nuevo = new NodoSimple(dato);
        if (!esVacio()){ /* Si la lista no esta vacia */
            nuevo.setSiguiente(primero); /*modificador*/
        }
        primero = nuevo;
        tamanio++;
        return this;
    }
    /*----------------------------------------Eliminar por el primero --------------------------------------------- */
    public ListaSimple eliminarPrimero() throws Exception {
        exeptionListaVacia();
        NodoSimple auxiliar;
        auxiliar = primero;
        if ( primero.getSiguiente() != null){ /*si la lista tiene mas de un elemento*/
            primero = auxiliar.getSiguiente();
            auxiliar.setSiguiente(null);
        }else{
            primero = null;
        }
        tamanio--;
        return this;
    }
    /* -----------------------------------Insertar final-------------------------------------------------------------*/
    public ListaSimple insertarFinal(Tipo dato){
        NodoSimple nuevo = new NodoSimple(dato);
        if (!esVacio()){
            NodoSimple auxiliar= primero;
            auxiliar = recorrerUnoAntesFinal(auxiliar);
            //si auxiliar apunta a null eso quiere decir que solo hay un nodo
            if (auxiliar.getSiguiente() != null){
                //apuntamos al ultimo nodo
                auxiliar = auxiliar.getSiguiente();
            }

            auxiliar.setSiguiente(nuevo);
        }else{
            primero = nuevo;
        }
        tamanio++;
        return this;
    }
    /* -----------------------------------Eliminar final-------------------------------------------------------------*/
    public ListaSimple eliminarFinal() throws Exception {
        exeptionListaVacia();
        NodoSimple auxiliar= primero;
        //si la lista tiene un solo nodo retorna ese sino retorna un nodo antes del final
        auxiliar = recorrerUnoAntesFinal(auxiliar);

        //si tiene mas de un nodo
        if (auxiliar.getSiguiente() != null){
            auxiliar.setSiguiente(null);
        }else {
            primero = null;
        }
        tamanio--;
        return this;
    }

    /* -----------------------------------Eliminar elemento buscado---------------------------------------------------*/
    public ListaSimple eliminarElementoBuscado(Tipo datoB) throws Exception {
        exeptionListaVacia();
        NodoSimple aux = primero;
        aux = recorrerUnNodoAnteriorElementoB(aux, datoB);
        //si solo tiene un nodo
        if (aux.getSiguiente() != null){
            if (aux.getDato() == datoB){
                //si es el primer nodo
                primero = aux.getSiguiente();
            } else if (aux.getSiguiente().getSiguiente() != null){
                //si no es el nodo final ni el primero
                NodoSimple auxAnterior = aux;
                aux = aux.getSiguiente();
                auxAnterior.setSiguiente(aux.getSiguiente());
            }
            //si es el primer nodo
            //si el nodo buscado es el ultimo
            // si es un nodo intermedio
            aux.setSiguiente(null);
        }else {
            primero = null;
        }
        tamanio--;
        return this;
    }

    /* -----------------------------------Insertar antes elemento buscado---------------------------------------------*/
    public ListaSimple insertarAntesElementoBuscado(Tipo datoBuscado, Tipo datoInsertar) throws Exception {
        exeptionListaVacia();
        NodoSimple nuevo = new NodoSimple(datoInsertar);
        NodoSimple aux = primero;
        aux = recorrerUnNodoAnteriorElementoB(aux, datoBuscado);
        if (aux.getSiguiente() != null && aux.getDato() != datoBuscado){
            //la lista tiene de 2 a más elementos y no insertamos antes del primero
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
        }else {
            nuevo.setSiguiente(primero);
            primero = nuevo;
        }
        tamanio++;
        return this;
    }
    /*----------------------------------Eliminar antes elemento buscado-----------------------------------------------*/
    public ListaSimple eliminarAntesElementoBuscado(Tipo datoB) throws Exception {
        exeptionListaVacia();
        NodoSimple aux = primero;
        aux = recorrerDosNodoAnteriorElementoB(aux, datoB);
        //si solo tiene un nodo o el nodo buscado es el primero
        if (aux.getDato() != datoB){
            if (aux.getSiguiente().getDato() != datoB){
                NodoSimple auxAnterior = aux;
                aux = aux.getSiguiente();
                auxAnterior.setSiguiente(aux.getSiguiente());
            }else {
                /*si el nodo buscado es el segundo*/
                primero = aux.getSiguiente();
            }
            // si es un nodo intermedio
            aux.setSiguiente(null);
        }else {
            throw new Exception("No existe un nodo antes de " + aux.getDato());
        }
        tamanio--;
        return this;
    }
    /* ---------------------------------Insertar despues elemento buscado---------------------------------------------*/
    public ListaSimple insertarDespuesElementoBuscado(Tipo datoB, Tipo datoI) throws Exception {
        exeptionListaVacia();
        NodoSimple nuevo = new NodoSimple(datoI);
        /*si el primero es el dato buscado */
//        if (primero.getDato() == datoB ){
//            if (primero.getSiguiente() != null){
//                nuevo.setSiguiente(primero.getSiguiente());
//            }
//            primero.setSiguiente(nuevo);
//        }else {
//            NodoSimple aux = primero;
//            aux = recorrerNodoAnteriorElementoB(aux, datoB);
//            //posicionarnos en el nodo buscado
//            aux = aux.getSiguiente();
//            nuevo.setSiguiente(aux.getSiguiente());
//            aux.setSiguiente(nuevo);
//        }
        NodoSimple aux = primero;
        if (primero.getDato() != datoB ) {
            aux = recorrerUnNodoAnteriorElementoB(aux, datoB);
            //posicionarnos en el nodo buscado
            aux = aux.getSiguiente();
        }
        if (aux.getSiguiente() != null){ /*si existe mas de un nodo*/
            nuevo.setSiguiente(aux.getSiguiente());
        }
        aux.setSiguiente(nuevo);
        tamanio++;
        return this;
    }
    /* ---------------------------------Eliminar despues elemento buscado---------------------------------------------*/
    public ListaSimple eliminarDespuesElementoBuscado(Tipo datoB) throws Exception {
        exeptionListaVacia();
        NodoSimple aux = primero;
        aux = recorrerElementoBuscado(aux, datoB);
        if (aux.getSiguiente() != null){/*si no tiene un solo nodo o no se encuentre en el nodo final*/
            if (aux.getSiguiente().getSiguiente() != null){ /*Si no es el penultimo nodo*/
                NodoSimple aux1 = aux;
                aux = aux.getSiguiente();
                aux1.setSiguiente(aux.getSiguiente());
            }
            aux.setSiguiente(null);
        }else {
            throw new Exception("No hay un elemento despues de " + aux.getDato());
        }
        tamanio--;
        return this;
    }

    /* -----------------------------------Obtener tamaño de la lista--------------------------------------------------*/
    public int getTamanio() {
        return tamanio;
    }
    /*-------------------------------------toString------------------------------------------------------------------ */
    @Override
    public String toString(){
        String salida = "primero->";
        if (primero == null){
            return "La lista esta vacia";
        }
        NodoSimple actual =primero;
        while (actual.getSiguiente() != null){
            salida = salida + "[" + actual.getDato()+ "]->";
            actual = actual.getSiguiente();
        }
        //al ultimo nodo apunta  a null
        salida = salida + "[" + actual.getDato()+ "]-> null";
        return salida;
    }
    /*    public String toString() {
        String salida = "";
        NodoSimple actual;
        for (actual = primero; actual !=null; actual=actual.getSiguiente()){
            salida = salida + actual.getDato();
        }
        return salida;
    }*/
    /*------------------------------------utils----------------------------------------------------------------*/
    public void exeptionListaVacia() throws Exception {
        if (esVacio()){
            throw new Exception("La lista esta vacia");
        }
    }
    public NodoSimple recorrerUnoAntesFinal(NodoSimple aux){
        //precondicion la lista no debe de estar vacia
        //si son mas de 1 elemento
        if (aux.getSiguiente() != null){
            while (aux.getSiguiente().getSiguiente() != null){
                aux = aux.getSiguiente();
            }
        }
        //si la lista tiene un solo nodo retorna ese, sino retorna un nodo antes del final
        return aux;
    }



    /*------------------------------------Recorridos ElementoBuscado--------------------------------------------------*/
    private NodoSimple recorrerElementoBuscado(NodoSimple aux, Tipo datoB) throws Exception {
        while (aux.getDato() != datoB && aux.getSiguiente() != null){
            aux = aux.getSiguiente();
        }
        if (aux.getDato() != datoB){
            throw new Exception("No se encontro el elemento Buscado");
        }
        return aux;
    }
    public NodoSimple recorrerUnNodoAnteriorElementoB(NodoSimple aux, Tipo datoBuscado) throws Exception {
        //precoindicion la lista no debe estar vacia
        //comprobar que el nodoB no es el primero
        if (aux.getDato() != datoBuscado){
            /*mientras haya mas de un nodo y no lleguemos al nodo buscado recorrido y se posiciona a un nodo anterior del buscado*/
            while (aux.getSiguiente()!= null && aux.getSiguiente().getDato() != datoBuscado){
                aux = aux.getSiguiente();
            }
            /*si el nodo en el que estamos apunta a null significa q no existe el elemento(si solo hay un nodo o si recorrimos hasta el final)*/
            if (aux.getSiguiente() == null){
                throw new Exception("No se encontro el elemento buscado");
            }
        }

        return aux;
    }
    public NodoSimple recorrerDosNodoAnteriorElementoB(NodoSimple aux, Tipo datoBuscado) throws Exception {
        //precoindicion la lista no debe estar vacia
        //Retornamos el primer nodo si el dato buscado se enuentra en el primero o segundo nodo
        if (aux.getDato() != datoBuscado && aux.getSiguiente().getDato() != datoBuscado){
            //System.out.println("no es el primero ni el segundo " + aux.getDato() + " => " + datoBuscado);
            /*mientras haya mas de 3 nodos*/
            while (aux.getSiguiente().getSiguiente()!= null && aux.getSiguiente().getSiguiente().getDato() != datoBuscado){
                aux = aux.getSiguiente();
            }
            if (aux.getSiguiente().getSiguiente() == null){
                throw new Exception("No se encontro el elemento buscado");
            }
        }
        //System.out.println(aux.getDato());
        return aux;
    }
    /*--------------------------------------Retornar Dato indice------------------------------------------------   */
    public Tipo getDatoIndex(int index){
        NodoSimple actual = primero;
        int contador = 0;

        while(contador < index){
            actual = actual.getSiguiente();
            contador++;
        }
        
        return (Tipo) actual.getDato();
    }
}
