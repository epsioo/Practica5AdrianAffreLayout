

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import static javax.swing.SpringLayout.SOUTH;

import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLEditorKit;

public class Instalacion extends JFrame {
    JPanel panelprincipal, primeraTarjeta, segundaTarjeta, terceraTarjeta, cuartaTarjeta ,quintaTarjeta;
    JPanel panelInferior, paneldibujo, panelBajo, panelEast  ;
    JTextPane datos;
    FlowLayout fl, f2;
    CardLayout tarjetas;
    JTextArea informacion;
    JButton siguiente, cancelar, atras,generar,validar;
    JLabel nombre, email, contraseña;
    JTextField textonombre, textoemail,errorEmail, errorContr;
    JPasswordField textocontraseña;
    JComboBox Provincia, pais;
    JCheckBox confirmar;
    File ruta;
    int numeropanel=1;
    JButton salir,guardar;
    boolean comprobar = false;
    boolean comprobar2 = false;
    JFileChooser jf;
    JPanel ventana;
    String almacenado;




    public Instalacion(){
        initdiseño();
        inittarjeta1();
        inittarjeta2();
        inittarjeta3();
        inittarjeta4();
        inittarjeta5();
        initbotones();
        initcontrolbotones();


    }
    // con este paint definiremos como sea nuestro dibujo
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        GradientPaint gp = new GradientPaint(20, 40, Color.BLACK, 100, 150, Color.white);
        g2d.setPaint(gp);
        g2d.fillRect(5, 10, 120, 150);

        g2d.setColor(Color.pink);
        g2d.fillOval(20, 40, 100, 100);
        g2d.setColor(Color.black);
        g2d.drawOval(20, 40, 100, 100);


        g2d.setColor(Color.gray);
        g2d.fillOval(20, 50, 80, 80);
        g2d.setColor(Color.black);
        g2d.drawOval(20, 50, 80, 80);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(20, 65, 80, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(20, 65, 80, 50);

        g2d.setColor(Color.BLUE);
        g2d.fillOval(35, 67, 45, 45);

        g2d.setColor(Color.black);
        g2d.fillOval(43, 76, 25, 25);


    }

    public void initcontrolbotones() {
        //este boton nos ayudara a avanazar atraves de las ventana
        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarjetas.next(panelprincipal);
                numeropanel=numeropanel+1;
                //mediante el contador numeropanel podemos controlar el movimiento dentro de los paneles
                if (numeropanel==5||numeropanel==4||numeropanel==2){
                    siguiente.setEnabled(false);
                }else{
                    siguiente.setEnabled(true);
                }
                atras.setEnabled(true);
            }
        });
        //si en algun caso hay algun momemeto queremos retroceder a la ventana anterior nos basta con ir hacia atras
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarjetas.previous(panelprincipal);
                numeropanel=numeropanel-1;
                if (numeropanel==1){
                    atras.setEnabled(false);
                }else{
                    atras.setEnabled(true);
                }
                siguiente.setEnabled(true);
            }
        });
        // si queremos  cancelar la operacion nos bastaria con darle a cancelar
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void initbotones() {
        siguiente = new JButton("SIGUIENTE");
        cancelar = new JButton("CANCELAR");
        atras = new JButton("ATRAS");
        atras.setEnabled(false);
        siguiente.setEnabled(true);

        panelInferior.add(cancelar);
        panelInferior.add(atras);
        panelInferior.add(siguiente);
    }

    private void inittarjeta5() {

        //añadimos un boton para finalizar la instalacion
        salir = new JButton("salir");
        quintaTarjeta.add(salir);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //y creamos otro boton con el cual vamos a selecionar que txt nos guarde los datos
        guardar = new JButton("guardar");
        quintaTarjeta.add(guardar);


        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser elegirruta = new JFileChooser();
                elegirruta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int resultado = elegirruta.showOpenDialog(null);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    ruta = elegirruta.getSelectedFile();
                }
                File nuevofichero = new File(ruta, "Nuevofichero.txt");

                try (FileWriter fw = new FileWriter(nuevofichero)) {
                    // no consegui almacenar la informacion entonces se la escribo directamente
                    fw.write(textonombre.getText());
                    fw.write(" ");
                    fw.write(textocontraseña.getText());
                    fw.write(" ");
                    fw.write(textoemail.getText());
                    fw.write(" ");
                    fw.write(String.valueOf(pais.getSelectedItem()));
                    fw.write(" ");
                    fw.write(String.valueOf(Provincia.getSelectedItem()));
                    fw.flush();

                } catch (IOException ioException) {
                    ioException.printStackTrace();

                }
            }
        });

    }
    private void inittarjeta4() {
        // creamos el panel datos con el cual vamos a instertar todas los datos selecionados
        datos = new JTextPane();
        cuartaTarjeta.add(datos);
        datos.setFocusable(false);
        datos.setBackground(Color.green);
        datos.setPreferredSize(new Dimension(400, 200));

        //para seguir a delante deberemos confirmar que los datos estan en orden
        confirmar = new JCheckBox("Aceptar Datos");
        cuartaTarjeta.add(confirmar);
        confirmar.setBackground(Color.WHITE);
        confirmar.setPreferredSize(new Dimension(400, 30));
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siguiente.setEnabled(true);
            }
        });


        confirmar.setBackground(Color.WHITE);
        confirmar.setPreferredSize(new Dimension(400, 30));
        // con el HTMLeditor vamos a organizar la informacion de la manera que nos gustaría que se presente en nuestro jtextpanel
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        datos.setEditorKit(htmlEditorKit);
        generar=new JButton("generar");
        generar.setFont(new Font("Monospaced",Font.PLAIN,16));
        generar.setOpaque(true);
        generar.setBackground(Color.WHITE);
        generar.setBorder(new LineBorder(Color.DARK_GRAY));
        generar.setForeground(Color.BLACK);
        cuartaTarjeta.add(generar);




        //el boton generar nos genera la informacion que hemos escogido dentro de jtextpanel
        generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                datos.setText(

                        "<span style='margin-left:50px;'>&nbsp;&nbsp;Nombre </span>" + textonombre.getText() + "<br>" +
                                "<i>Email</i> " + textoemail.getText() + "<br>" +
                                "<i>Contraseña</i> " + textocontraseña.getText() + "<br>" +
                                "<i>Pais</i><br>" + pais.getSelectedItem() + "<br>" +
                                "<i>Provincia</i>" + Provincia.getSelectedItem() +"<br>"

                );
            }

        });
        //almacenado = datos.getText();
        System.out.println(almacenado);
        cuartaTarjeta.add(datos);

    }
    private void inittarjeta3() {
        //creamos dos JcomoBox para selecionar entre varios paises
        pais = new JComboBox();
        pais.addItem("pais");
        pais.addItem("España");
        pais.addItem("EEUU");
        pais.setPreferredSize(new Dimension(200, 30));
        terceraTarjeta.add(pais);

        //normalmente se debe escoger entre dos txt para conseguir que cada pais tenga su numero especifico de provincias
        Provincia = new JComboBox();
        Provincia.setPreferredSize(new Dimension(200, 30));
        terceraTarjeta.add(Provincia);

        pais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pais.getSelectedItem() == "EEUU") {
                    Provincia.removeAllItems();
                    Provincia.getSelectedItem();

                    try {
                        File rutaEEUU = new File("C:\\accdat\\EEUU.txt");
                        FileReader escribir = new FileReader(rutaEEUU);
                        BufferedReader BR = new BufferedReader(escribir);
                        String linea = BR.readLine();

                        while (linea != null) {
                            Provincia.addItem(linea);
                            linea = BR.readLine();
                        }
                    } catch (Exception Ex) {
                    }
                }

                if (pais.getSelectedItem() == "España") {
                    Provincia.removeAllItems();
                    Provincia.setSelectedItem(null);
                    try {
                        File lista = new File("C:\\accdat\\espana.txt");
                        FileReader lector = new FileReader(lista);
                        BufferedReader BR = new BufferedReader(lector);
                        String linea = BR.readLine();

                        while (linea != null) {
                            Provincia.addItem(linea);
                            linea = BR.readLine();
                        }
                    } catch (Exception Ex) {

                    }
                }
            }
        });


    }
    private void inittarjeta2() {
        //creamos tres Jlabels que se llamaran nombre,email y contraseña
        nombre = new JLabel("Nombre");
        segundaTarjeta.add(nombre);
        nombre.setBounds(20, 10,100, 30);

        email = new JLabel("Email");
        segundaTarjeta.add(email);
        email.setBounds(20, 70,100, 30);

        contraseña = new JLabel("Contraseña");
        segundaTarjeta.add(contraseña);
        contraseña.setBounds(20, 130,100, 30);

        //generamos dos textfield y text password
        textonombre = new JTextField("");
        segundaTarjeta.add(textonombre);
        textonombre.setBounds(90,10,100,30);


        textoemail = new JTextField("");
        segundaTarjeta.add(textoemail);
        textoemail.setBounds(90,70,100,30);

        textocontraseña = new JPasswordField("");
        textocontraseña.setBounds(90, 130, 100, 30);
        segundaTarjeta.add(textocontraseña);

        //creamos dos textfields que apareceran si tenemos un error a la hora de rellenar los campos
        errorEmail = new JTextField("* El email debe contener '@' ");
        segundaTarjeta.add(errorEmail);
        errorEmail.setBounds(200,70,250,30);
        errorEmail.setForeground(Color.red);
        errorEmail.setBorder(new LineBorder(Color.WHITE));
        errorEmail.setVisible(false);


        errorContr = new JTextField("8 y 16 caracteres, mayúscula, una minúscula y  un caracter que no sea letra ni número");
        segundaTarjeta.add(errorContr);
        errorContr.setBounds(200,130,250,30);
        errorContr.setForeground(Color.red);
        errorContr.setBorder(new LineBorder(Color.WHITE));
        errorContr.setVisible(false);
        //este boton validar nos ayuda a comprovar si hemos escrito correctamente en los text fiels
        validar=new JButton("Validar");
        validar.setFont(new Font("Monospaced",Font.PLAIN,14));
        validar.setBounds(70,200,100,100);
        segundaTarjeta.add(validar);
        this.validar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; !Instalacion.this.comprobar; ++i) {
                    String mail = Instalacion.this.textoemail.getText();
                    if (mail.charAt(i) == '@') {
                        Instalacion.this.comprobar = true;
                        Instalacion.this.errorEmail.setVisible(false);
                    } else {
                        Instalacion.this.errorEmail.setVisible(true);
                    }
                }
                if (!Instalacion.this.textocontraseña.getPassword().equals("")) {
                    String textoc =Instalacion.this.textocontraseña.getPassword().toString();
                    int longitud = 0;
                    int contador = 0;
                    int contadorm = 0;
                    int simbolos = 0;
                    int contmy = 0;

                    for (int i = 0; i < Instalacion.this.textocontraseña.getPassword().length; i++) { //Fuente: https://es.stackoverflow.com/questions/309558/c%c3%b3mo-validar-caracteres-especiales-en-java

                        if ((textoc.charAt(i) >= 47 && textoc.charAt(i) <= 58) ||//numeros
                                (textoc.charAt(i) >= 64 && textoc.charAt(i) <= 91) ||//mayusculas
                                (textoc.charAt(i) >= 32 && textoc.charAt(i) <= 44)//signos
                                || (textoc.charAt(i) >= 97 && textoc.charAt(i) <= 122)) {//minusculas

                        }

                        if (textoc.charAt(i) >= 47 && textoc.charAt(i) <= 58) {// Cuenta la cantidad de numeros
                            contador++;
                            System.out.println(contador);
                        }

                        if ((textoc.charAt(i) > 32 && textoc.charAt(i) < 44)) { // Cuenta la cantidad signos
                            simbolos++;
                            System.out.println(simbolos);
                        }

                        if (textoc.charAt(i) >= 64 && textoc.charAt(i) <= 91) {// Cuenta la cantidad de mayusculas
                            contmy++;
                            System.out.println(contmy);
                        }
                        if (textoc.charAt(i) >= 97 && textoc.charAt(i) <= 122) {// Cuenta la cantidad de minusculas
                            contadorm++;
                            System.out.println(contadorm);
                        }
                        longitud++;
                        System.out.println(longitud);
                    }
                    if (contmy < 1) {                // Revisa la longitud minima de 8 caracteres del password
                        Instalacion.this.errorContr.setText("no tiene caracteres especiales como ( ! # $ % & ' ( ) + - )");
                        Instalacion.this.errorContr.setVisible(true);

                    }
                    else{

                    }
                    if (longitud < 1) {              // Revisa que el password contenga minimo 1 numero
                        Instalacion.this.errorContr.setText( "Medio");
                        Instalacion.this.errorContr.setVisible(true);
                    }
                    else{

                    }

                    if (longitud< 1) {                            // Revisa que el password contenga minimo 1 mayuscula
                        Instalacion.this.errorContr.setText( "Facil");
                        Instalacion.this.errorContr.setVisible(true);
                    }
                    else{

                    }

                    if (longitud< 5) {                // Revisa la longitud minima de 8 caracteres del password
                        Instalacion.this.errorContr.setText("No cumple con el mínimo de caracteres!");
                        Instalacion.this.errorContr.setVisible(true);
                    }
                    else {
                        Instalacion.this.errorContr.setVisible(false);
                        Instalacion.this.comprobar2 = true;
                    }

                    if (Instalacion.this.comprobar && Instalacion.this.comprobar2) {
                        Instalacion.this.siguiente.setEnabled(true);
                    }
                }
            }

        });

    }
    private void inittarjeta1() {
        // en esta tarjeta se encuentra la bienbenida
        informacion = new JTextArea("Bienvenido a la instalación. Pulse Continuar para el siguiente paso.");
        primeraTarjeta.add(informacion);
        informacion.setFocusable(false);
    }

    private void initdiseño() {
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        //estos dos Flow layout nos ayuda a a que la primera, la tercera, la cuarta y quinta ventana se adapten dependiendo de como sea el tamaño de la ventana
        fl = new FlowLayout();
        f2 = new FlowLayout();


        panelBajo = new JPanel();
        panelBajo.setPreferredSize(new Dimension(100, 50));
        add(panelBajo, SOUTH);
        //en este panel inferior tendremos los botones
        panelInferior = new JPanel();
        panelInferior.setBackground(Color.WHITE);
        // este sera el panel izquierdo donde tendremos nuestro dibujo
        paneldibujo = new JPanel();
        paneldibujo.setBackground(Color.green);
        paneldibujo.setPreferredSize(new Dimension(120, 100));
        //generamos el panl derecho
        panelEast = new JPanel();
        panelEast.setBackground(Color.green);
        panelEast.setPreferredSize(new Dimension(120, 100));

        add(panelInferior, BorderLayout.SOUTH);
        add(paneldibujo, BorderLayout.WEST);
        add(panelEast, BorderLayout.EAST);

        tarjetas = new CardLayout();
        //el panelprincipal es donde añadiremos las 5 cinco tarjetas
        panelprincipal = new JPanel();
        panelprincipal.setLayout(tarjetas);

        //generamos las 5 tarjetas
        primeraTarjeta = new JPanel();
        segundaTarjeta = new JPanel();
        terceraTarjeta = new JPanel();
        cuartaTarjeta = new JPanel();
        quintaTarjeta = new JPanel();

        //les damos un color a los paneles
        primeraTarjeta.setBackground(Color.WHITE);
        segundaTarjeta.setBackground(Color.WHITE);
        terceraTarjeta.setBackground(Color.WHITE);
        cuartaTarjeta.setBackground(Color.WHITE);
        quintaTarjeta.setBackground(Color.WHITE);


        //añadimos las 5 tarjetas dentro del panel principal
        panelprincipal.add(primeraTarjeta, "Paso 1");
        panelprincipal.add(segundaTarjeta, "Paso 2");
        panelprincipal.add(terceraTarjeta, "Paso 3");
        panelprincipal.add(cuartaTarjeta, "Paso 4");
        panelprincipal.add(quintaTarjeta, "Paso 5");

        tarjetas.show(panelprincipal, "Paso 1");
        add(panelprincipal, BorderLayout.CENTER);
        //generamos un setlayout null para que la posicion de los botones se vea representada dentro del jpanel
        segundaTarjeta.setLayout(null);
    }

    public static void main(String[] args) {

        Instalacion formulario = new Instalacion();
        formulario.setBounds(150, 150, 700, 400);
        formulario.setTitle("Tienda de deportes");
        formulario.setDefaultCloseOperation(EXIT_ON_CLOSE);
        formulario.setVisible(true);
        formulario.setResizable(true);

    }
}
