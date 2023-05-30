package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class) // Extensão passada paras anotações do @Mock serem injetados
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CadastroEditorComMockComAnotacoesTest {

    @Spy
    Editor editor = new Editor(null, "Murillo", "murillo@email.com", BigDecimal.TEN, true);

    @Captor
    ArgumentCaptor<Mensagem> mensagemArgumentCaptor;

    // Instanciando classes mockito
    // Sempre a cada novo teste será instanciado um novo mock
    @Mock
    ArmazenamentoEditor armazenamentoEditorMock;

    @Mock
    GerenciadorEnvioEmail gerenciadorEnvioEmailMock;

    // o mockito junto ao junit irá adicionar esses dois mocks pra dentro do cadastro de editor e criar uma instancia completa
    @InjectMocks
    CadastroEditor cadastroEditor;

    @BeforeEach
    void init() {
        // Mockito.any é muito útil em diversos casos principalmente quando temos muitos parâmetros
        // nos métodos que estamos realizando o mock
        Mockito.when(armazenamentoEditorMock.salvar(Mockito.any(Editor.class)))
                // Alterando um parâmetro passado para ficar mais dinâmico
                // Esse método thenAnswer pega a própria invocação do método
                .thenAnswer(invocationOnMock -> {
                    Editor editorPassado = invocationOnMock.getArgument(0, Editor.class);
                    editorPassado.setId(1L);
                    return editorPassado;
                });
    }

    @Test
    void Dado_um_editor_valido_Quando_criar_Entao_deve_retornar_um_id_de_cadastro() {
        Editor editorSalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editorSalvo.getId());
    }

    @Test
    void Dado_um_editor_valido_Quando_criar_Entao_deve_chamar_metodo_salvar_do_armazenamento() {
        // Verificar se o método realmente está sendo chamado e passando o parâmetro de editor
        cadastroEditor.criar(editor);
        // verify(mock, numero de vezes chamado)
        Mockito.verify(armazenamentoEditorMock, Mockito.times(1))
                .salvar(Mockito.eq(editor)); // Também pode usar Mockito.any(Editor.class), usando assim só pra ter certeza que é o parâmetro que estamos passando
    }

    // Verificar se realmente não envia email em caso de exception
    @Test
    void Dado_um_editor_Quando_criar_e_lancar_exception_ao_salvar_Entao_nao_deve_enviar_email() {
        // Forçando a retornar RunTimeException
        Mockito.when(armazenamentoEditorMock.salvar(editor)).thenThrow(new RuntimeException());
        assertAll("Não deve enviar e-mail quando lançar Exception do armazenamento",
                // Verificando se lançou RunTimeException
                () -> Assertions.assertThrows(RuntimeException.class, () -> cadastroEditor.criar(editor)),
                // Verificando se não passou no enviarEmail
                () -> Mockito.verify(gerenciadorEnvioEmailMock, Mockito.never()).enviarEmail(Mockito.any()));
        // Sem o assertAll ele lança exceção e não chega no verify
    }

    @Test
    void Dado_um_editor_valido_Quando_cadastrar_Entao_deve_enviar_email_com_destino_ao_editor() {
        //ArgumentCaptor<Mensagem> mensagemArgumentCaptor = ArgumentCaptor.forClass(Mensagem.class);

        Editor editorSalvo = cadastroEditor.criar(editor);

        Mockito.verify(gerenciadorEnvioEmailMock).enviarEmail(mensagemArgumentCaptor.capture());

        Mensagem mensagem = mensagemArgumentCaptor.getValue();

        assertEquals(editorSalvo.getEmail(), mensagem.getDestinatario());
    }

    @Test
    void Dado_um_editor_valido_Quando_cadastrar_Entao_deve_verificar_o_email() {
        // Editor editorSpy = Mockito.spy(editor);
        // cadastroEditor.criar(editorSpy);
        cadastroEditor.criar(editor);
        // atLeast verificamos se o getEmail foi chamado pelo menos uma vez no teste de criação
        // Mockito.verify(editorSpy, Mockito.atLeast(1)).getEmail();
        Mockito.verify(editor, Mockito.atLeast(1)).getEmail();
    }

}
