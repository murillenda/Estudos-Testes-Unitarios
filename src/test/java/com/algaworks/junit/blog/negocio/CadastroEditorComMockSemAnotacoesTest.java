package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CadastroEditorComMockSemAnotacoesTest {

    CadastroEditor cadastroEditor;
    Editor editor;

    @BeforeEach
    void init() {
        editor = new Editor(null, "Murillo", "murillo@email.com", BigDecimal.TEN, true);

        // Criacao mockito
        ArmazenamentoEditor armazenamentoEditorMock = Mockito.mock(ArmazenamentoEditor.class);

        // Definicao comportamento desejado mockito
        Mockito.when(armazenamentoEditorMock.salvar(editor))
                .thenReturn(new Editor(1L, "Murillo", "murillo@email.com", BigDecimal.TEN, true));

        GerenciadorEnvioEmail gerenciadorEnvioEmailMock = Mockito.mock(GerenciadorEnvioEmail.class);

        cadastroEditor = new CadastroEditor(armazenamentoEditorMock, gerenciadorEnvioEmailMock);
    }

    @Test
    void Dado_um_editor_valido_Quando_criar_Entao_deve_retornar_um_id_de_cadastro() {
        Editor editorSalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editorSalvo.getId());
    }

}
