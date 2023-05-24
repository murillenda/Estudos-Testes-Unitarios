package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class) // Extensão passada paras anotações do @Mock serem injetados
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CadastroEditorComMockComAnotacoesTest {

    Editor editor;

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
        editor = new Editor(null, "Murillo", "murillo@email.com", BigDecimal.TEN, true);

        Mockito.when(armazenamentoEditorMock.salvar(editor))
                .thenReturn(new Editor(1L, "Murillo", "murillo@email.com", BigDecimal.TEN, true));
    }

    @Test
    void Dado_um_editor_valido_Quando_criar_Entao_deve_retornar_um_id_de_cadastro() {
        Editor editorSalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editorSalvo.getId());
    }

}
