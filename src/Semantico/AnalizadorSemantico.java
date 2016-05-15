//Analizador semantico
package Semantico;

public class AnalizadorSemantico {
  private Herramientas.Errores errores;
  private Herramientas.Errores warnings;
  private Herramientas.TablaSimbolos tabla_simbolos;
  private Herramientas.CompSociedad lista_agentes;

  public AnalizadorSemantico(Herramientas.CompSociedad agentes, Herramientas.Errores err, Herramientas.Errores warn) {
//  tabla_simbolos = t_s;
  errores = err;
  warnings = warn;
  lista_agentes = agentes;
  }

}
