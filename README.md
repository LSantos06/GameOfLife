GameOfLife
==========
Projeto da disciplina de Programação Orientada a Objetos (1/2016) na Universidade de Brasília.

Baseado nos códigos disponibilizados no ambiente moodle da disciplina as seguintes melhorias devem ser implementadas no algoritmo do GameOfLife:

* Implemente uma nova opção de menu que faz com que **próximas gerações sejam computadas automaticamente**. Observe que a implementação atual não suporta a noção de ambiente infinito (as células próximas aos limites do tabuleiro não possuem oito células vizinhas). Corrija essa falha de implementação.

* Implemente uma **interface gráfica** para o GameOfLife reusando as classes existentes. A interface gráfica pode ser baseada em *Java Swing*, *SWT* ou *Android*.

* Torne a implementação mais extensível com o uso do padrão **injeção de dependância (ID)**, de tal forma que os objetos que implementam os diferentes algoritmos para calcular as regras de derivação sejam injetados no programa (em vez de diretamente instanciados). Alternativas tecnológicas: *Spring framework (apenas a parte de ID)*, *Google Guice* ou *Java reflection (introspecção)*.

##### Material de estudo:
[Tutorial *Spring Framework*](https://www.youtube.com/playlist?list=PLC97BDEFDCDD169D7)
