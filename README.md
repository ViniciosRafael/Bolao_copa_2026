# Bolao_copa_2026


DIAGRAMA DE CLASSE DISPONIVEL NA PASTA PRINCIPAL

SistemaBolao/
└── src/main/java/com/mycompany/sistemabolao/
    ├── SistemaBolao.java        # Ponto de entrada (main)
    ├── MenuSistema.java         # Menu interativo via console
    ├── ExibicaoSistema.java     # Exibição de resultados e ranking
    │
    ├── Usuario.java             # Classe abstrata base
    ├── Administrador.java       # Herda Usuario
    ├── Participante.java        # Herda Usuario, possui lista de palpites
    │
    ├── Selecao.java             # Seleção com nome, sigla e jogadores
    ├── Jogador.java             # Jogador com posição, camisa e peso de pontuação
    ├── Jogo.java                # Jogo entre duas seleções com data/hora
    ├── Palpite.java             # Palpite de um participante em um jogo
    │
    ├── ResultadoGols.java       # Resultado real com gols por jogador
    ├── GolsPorJogador.java      # Gols marcados por um jogador específico
    ├── Ranking.java             # Ordenação dos participantes por pontuação
    │
    ├── Pontuacao.java           # Interface Strategy de pontuação
    ├── RegraVencedor.java       # +3 pts por acertar o vencedor
    ├── RegraGolsEquipe.java     # +2 pts por acertar gols de cada time
    ├── RegraPlacarExato.java    # +5 pts por acertar o placar exato
    └── RegraGolsJogador.java    # +2 pts por gol do jogador apostado
```

## 📋 Fluxo de uso

### 1. Cadastrar seleção `[1]`
Informe o nome da seleção, a sigla (ex: `BRA`) e os jogadores com posição, número de camisa e peso de pontuação.

> O **peso de pontuação** é um multiplicador usado ao apostar em um jogador. Exemplo: `1.0` = padrão, `1.5` = artilheiro favorito.

### 2. Cadastrar jogo `[2]`
Selecione o mandante e o visitante (entre as seleções já cadastradas) e informe a data e hora do jogo.

> É necessário ter pelo menos **2 seleções** cadastradas.

### 3. Cadastrar participante `[3]`
Informe nome e e-mail. O participante começa com **0 pontos** e sem palpites.

### 4. Registrar palpite `[4]`
Escolha um participante e um jogo. Informe o placar previsto (gols mandante × gols visitante) e, opcionalmente, o nome de um jogador apostado para marcar gol.

> Validações:
> - Não é possível palpitar em jogo já finalizado.
> - Cada participante pode fazer apenas **um palpite por jogo**.

### 5. Registrar resultado `[5]`
Informe o placar real do jogo e os jogadores que marcaram gol (com a quantidade de gols de cada um).

O sistema calcula e atualiza automaticamente a pontuação de todos os participantes que apostaram naquele jogo.

### 6. Visualizar tudo `[6]`
Exibe um painel completo com:
- Seleções e jogadores cadastrados
- Jogos (agendados e finalizados)
- Participantes
- Palpites registrados
- Ranking atualizado

---

## 🏆 Sistema de pontuação

Quatro regras são aplicadas a cada palpite após o resultado real ser registrado:

| Regra | Condição | Pontos |
|---|---|---|
| `RegraVencedor` | Acertou quem venceu (ou empate) | +3 pts |
| `RegraGolsEquipe` | Acertou os gols do mandante | +2 pts |
| `RegraGolsEquipe` | Acertou os gols do visitante | +2 pts |
| `RegraPlacarExato` | Acertou o placar exato (mandante e visitante) | +5 pts |
| `RegraGolsJogador` | Jogador apostado marcou gol | +2 pts por gol |

> Exemplo: palpite `2 x 1`, resultado real `2 x 1`, jogador apostado marcou 2 gols → `3 + 2 + 2 + 5 + 4 = 16 pts`

As regras seguem o **padrão Strategy** — novas regras podem ser adicionadas implementando a interface `Pontuacao`:

```java
public interface Pontuacao {
    int calcular(Palpite palpite, Jogo jogo, ResultadoGols resultado);
}
```

---

## 🏗️ Arquitetura e padrões utilizados

**Herança**
`Usuario` (abstrato) → `Administrador` e `Participante`

**Strategy Pattern**
Interface `Pontuacao` com 4 implementações independentes de regras de pontuação, aplicadas via array em `Palpite.calcularPontuacao()`.

**Separação de responsabilidades**
- `MenuSistema` — controla o fluxo de entrada do usuário
- `ExibicaoSistema` — responsável exclusivamente pela apresentação
- `SistemaBolao` — ponto de entrada limpo, apenas inicializa o menu

**Encapsulamento**
Todos os atributos são `private` com getters/setters controlados.
