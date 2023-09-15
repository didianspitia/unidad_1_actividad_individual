import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Crear una lista para estadisticas

        String[] data = new String[15];

        data[0] =  "Nombre,Apellido,Edad,Genero,Nivel Socioeconimico,Acceso Superior,Tipo de institucion,Modalidad,Motivo";
        data[1] =  "Daniel,Jaramillo,23,Masculino,2,si,Publica,presencial,null";
        data[2] =  "Ana,Jiménez,17,Femenino,6,no,null,null,Economico";
        data[3] =  "Luis,García,20,Masculino,3,si,Publica,presencial,null";
        data[4] =  "Andrea,Fernández,21,Femenino,3,si,Privada,presencial,null";
        data[5] =  "Juan,Pérez,25,Masculino,2,si,Publica,remota,null";
        data[6] =  "Sofía,González,19,Femenino,5,si,Pública,remota,null";
        data[7] =  "Luis,Hernández,22,Masculino,1,no,null,null,economico";
        data[8] =  "Ana,Martínez,26,Femenino,2,no,null,null,economico";
        data[9] =  "Daniel,Rodríguez,21,Masculino,4,si,Privada,presencial,null";
        data[10] = "Andrea,García,24,Femenino,6,si,Pública,Presencial,null";
        data[11] = "Juan,Martínez,18,Masculino,6,si,Pública,presencial,null";
        data[12] = "Laura,Pérez,18,Femenino,2,no,null,null,Economicomico";
        data[13] = "Carlos,Jiménez,20,Masculino,3,si,Pública,presencial, null";
        data[14] = "Sofía,González,25,Femenino,2,no,null,null,Personal";
        
        String[][] registros = getRegistros(data);

        // Imprimir encabezados
        System.out.println("------------personas encuestadas-----------\n");
        for (int i = 0; i < registros[0].length; i++) {
            System.out.print(String.format("%-22s", registros[0][i]));
        }
        System.out.println();

        // Imprimir registros de datos
        for (int i = 1; i < registros.length; i++) {
            for (int j = 0; j < registros[i].length; j++) {
                System.out.print(String.format("%-22s", registros[i][j]));
            }
            System.out.println();
        }

        // Calcular la media, mediana y moda de "Acceso Superior"
        int[] accesoSuperiorCount = new int[2]; // Contador de "si" y "no"
        int totalRegistros = registros.length - 1; // Restamos 1 para excluir la fila de encabezado

        // Contar hombres y mujeres con acceso superior "si"
        int hombresConAcceso = 0;
        int mujeresConAcceso = 0;

        // Contar hombres y mujeres sin acceso superior "no"
        int hombresSinAcceso = 0;
        int mujeresSinAcceso = 0;

        // Contar modalidad presencial y virtual
        int modalidadPresencial = 0;
        int modalidadVirtual = 0;

        // Contar acceso superior por nivel socioeconómico
        int[] accesoSuperiorPorNivel = new int[3]; // Contador para niveles 1-2, 3-4, y 5-6

        for (int i = 1; i < registros.length; i++) {
            String accesoSuperior = registros[i][5];
            String genero = registros[i][3];
            String modalidad = registros[i][7];
            int nivelSocioeconomico = Integer.parseInt(registros[i][4]);

            if ("si".equals(accesoSuperior)) {
                accesoSuperiorCount[0]++;
                if ("Masculino".equalsIgnoreCase(genero)) {
                    hombresConAcceso++;
                } else if ("Femenino".equalsIgnoreCase(genero)) {
                    mujeresConAcceso++;
                }
            } else if ("no".equals(accesoSuperior)) {
                accesoSuperiorCount[1]++;
                if ("Masculino".equalsIgnoreCase(genero)) {
                    hombresSinAcceso++;
                } else if ("Femenino".equalsIgnoreCase(genero)) {
                    mujeresSinAcceso++;
                }
            }

            if ("presencial".equalsIgnoreCase(modalidad)) {
                modalidadPresencial++;
            } else if ("virtual".equalsIgnoreCase(modalidad)) {
                modalidadVirtual++;
            }

            if (nivelSocioeconomico >= 1 && nivelSocioeconomico <= 2) {
                accesoSuperiorPorNivel[0]++;
            } else if (nivelSocioeconomico >= 3 && nivelSocioeconomico <= 4) {
                accesoSuperiorPorNivel[1]++;
            } else if (nivelSocioeconomico >= 5 && nivelSocioeconomico <= 6) {
                accesoSuperiorPorNivel[2]++;
            }
        }

        double media = (double) accesoSuperiorCount[0] / totalRegistros; // Media (promedio)

        // Mediana
        String[] accesoSuperiorArray = new String[totalRegistros];
        for (int i = 1; i < registros.length; i++) {
            accesoSuperiorArray[i - 1] = registros[i][5];
        }
        Arrays.sort(accesoSuperiorArray);
        String mediana = accesoSuperiorArray[totalRegistros / 2];

        // Moda
        String moda = "";
        int maxCount = 0;

        for (int i = 0; i < accesoSuperiorArray.length; i++) {
            String current = accesoSuperiorArray[i];
            int count = 1;
            for (int j = i + 1; j < accesoSuperiorArray.length; j++) {
                if (current.equals(accesoSuperiorArray[j])) {
                    count++;
                }
            }
            if (count > maxCount) {
                moda = current;
                maxCount = count;
            }
        }

        // Calcular los porcentajes
        double porcentajeSi = (double) accesoSuperiorCount[0] / totalRegistros * 100;
        double porcentajeNo = (double) accesoSuperiorCount[1] / totalRegistros * 100;
        double porcentajeHombresConAcceso = (double) hombresConAcceso / accesoSuperiorCount[0] * 100;
        double porcentajeMujeresConAcceso = (double) mujeresConAcceso / accesoSuperiorCount[0] * 100;
        double porcentajeHombresSinAcceso = (double) hombresSinAcceso / totalRegistros * 100;
        double porcentajeMujeresSinAcceso = (double) mujeresSinAcceso / totalRegistros * 100;
        double porcentajeModalidadPresencial = (double) modalidadPresencial / totalRegistros * 100;
        double porcentajeModalidadVirtual = (double) modalidadVirtual / totalRegistros * 100;
        double porcentajeNivel1a2 = (double) accesoSuperiorPorNivel[0] / totalRegistros * 100;
        double porcentajeNivel3a4 = (double) accesoSuperiorPorNivel[1] / totalRegistros * 100;
        double porcentajeNivel5a6 = (double) accesoSuperiorPorNivel[2] / totalRegistros * 100;

        // Imprimir los resultados
        System.out.println("\n------------informacion------------");
        System.out.println("\nMedia de Acceso Superior: " + media);
        System.out.println("Mediana de Acceso Superior: " + mediana);
        System.out.println("Moda de Acceso Superior: " + moda);
        System.out.println("\nPorcentaje de personas que tiene acceso: " + porcentajeSi + "%");
        System.out.println("Porcentaje de personas que no tiene acceso: " + porcentajeNo + "%");
        System.out.println("\nPorcentaje de hombres con acceso superior: " + porcentajeHombresConAcceso + "%");
        System.out.println("Porcentaje de mujeres con acceso superior: " + porcentajeMujeresConAcceso + "%");
        System.out.println("Porcentaje de hombres sin acceso superior: " + porcentajeHombresSinAcceso + "%");
        System.out.println("Porcentaje de mujeres sin acceso superior: " + porcentajeMujeresSinAcceso + "%");
        System.out.println("\nPorcentaje de modalidad presencial :" + porcentajeModalidadPresencial + "%");
        System.out.println("Porcentaje de modalidad virtual :" + porcentajeModalidadVirtual + "%");
        System.out.println("\nPorcentaje de estrato 1 a 2 :" + porcentajeNivel1a2 + "%");
        System.out.println("Porcentaje de estrato 3 a 4 :" + porcentajeNivel3a4 + "%");
        System.out.println("Porcentaje de estrato 5 a 6 :" + porcentajeNivel5a6 + "%");
    }

    // Función para obtener los registros de una lista de datos
    public static String[][] getRegistros(String[] data) {
        String[][] registros = new String[data.length][9];
        for (int i = 0; i < data.length; i++) {
            String[] campos = data[i].split(",");
            for (int j = 0; j < campos.length; j++) {
                registros[i][j] = campos[j];
            }
        }
        return registros;
    }
}