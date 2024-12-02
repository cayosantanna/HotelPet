#!/bin/bash

# Diretório do repositório
REPO_DIR="/home/cayo/NetBeansProjects/HotelPet"

# Branch principal e branch local
BRANCH_MAIN="Main"
BRANCH_LOCAL="Cayo"

# Navegar até o repositório
cd "$REPO_DIR" || {
    echo "Erro: Diretório $REPO_DIR não encontrado!"
    exit 1
}

# Função para exibir mensagem e sair em caso de erro
erro() {
    echo "Erro: $1"
    exit 1
}

# Função para verificar conflitos pendentes
verifica_conflitos() {
    if ! git diff --quiet --name-only --diff-filter=U; then
        echo "⚠️ Conflitos de merge detectados! Resolva os conflitos antes de prosseguir."
        git status
        exit 1
    fi
}

# Menu interativo
echo -e "============================"
echo -e "   Gerenciador Git Script   "
echo -e "============================"
echo -e "Escolha uma opção:"
echo -e "1. Fazer Pull da branch principal ($BRANCH_MAIN)"
echo -e "2. Fazer Push para o repositório remoto (branch $BRANCH_LOCAL)"
echo -e "3. Sincronizar branch local ($BRANCH_LOCAL) com principal ($BRANCH_MAIN)"
echo -e "4. Alternar para outra branch"
echo -e "5. Automatizar Pull + Merge + Push"
echo -e "6. Sair"
read -p "Digite o número da opção desejada: " opcao

case $opcao in
    1)
        # Fazer pull da branch principal
        echo "➡️  Atualizando a branch principal ($BRANCH_MAIN)..."
        git fetch origin || erro "Falha no fetch!"
        git checkout "$BRANCH_MAIN" || erro "Falha ao mudar para a branch $BRANCH_MAIN!"
        git pull origin "$BRANCH_MAIN" || erro "Falha ao fazer pull da branch $BRANCH_MAIN!"
        echo "✅ Pull da branch $BRANCH_MAIN realizado com sucesso!"
        ;;
    2)
        # Fazer push da branch local
        echo "➡️  Enviando alterações da branch local ($BRANCH_LOCAL)..."
        git checkout "$BRANCH_LOCAL" || erro "Falha ao mudar para a branch $BRANCH_LOCAL!"
        if git rev-parse --abbrev-ref "$BRANCH_LOCAL"@{upstream} >/dev/null 2>&1; then
            git push origin "$BRANCH_LOCAL" || erro "Falha ao fazer push da branch $BRANCH_LOCAL!"
        else
            echo "⚠️  Configurando upstream para a branch $BRANCH_LOCAL..."
            git push --set-upstream origin "$BRANCH_LOCAL" || erro "Falha ao configurar upstream!"
        fi
        echo "✅ Push da branch $BRANCH_LOCAL realizado com sucesso!"
        ;;
    3)
        # Sincronizar branch local com a principal
        echo "➡️  Sincronizando a branch local ($BRANCH_LOCAL) com a principal ($BRANCH_MAIN)..."
        git fetch origin || erro "Falha no fetch!"
        git checkout "$BRANCH_LOCAL" || erro "Falha ao mudar para a branch $BRANCH_LOCAL!"
        git merge origin/"$BRANCH_MAIN" || erro "Falha ao fazer merge! Resolva conflitos e tente novamente."
        verifica_conflitos
        echo "✅ Sincronização concluída!"
        ;;
    4)
        # Alternar para outra branch
        read -p "Digite o nome da branch para alternar: " nova_branch
        git checkout "$nova_branch" || erro "Falha ao mudar para a branch $nova_branch! Verifique se ela existe."
        echo "✅ Mudança para a branch $nova_branch realizada com sucesso!"
        ;;
    5)
        # Automatizar Pull + Merge + Push
        echo "➡️  Automatizando Pull + Merge + Push..."
        git fetch origin || erro "Falha no fetch!"
        git checkout "$BRANCH_MAIN" || erro "Falha ao mudar para a branch $BRANCH_MAIN!"
        git pull origin "$BRANCH_MAIN" || erro "Falha ao fazer pull da branch $BRANCH_MAIN!"
        git checkout "$BRANCH_LOCAL" || erro "Falha ao mudar para a branch $BRANCH_LOCAL!"
        git merge "$BRANCH_MAIN" || erro "Falha ao fazer merge com a branch $BRANCH_MAIN!"
        verifica_conflitos
        if git rev-parse --abbrev-ref "$BRANCH_LOCAL"@{upstream} >/dev/null 2>&1; then
            git push origin "$BRANCH_LOCAL" || erro "Falha ao fazer push da branch $BRANCH_LOCAL!"
        else
            echo "⚠️  Configurando upstream para a branch $BRANCH_LOCAL..."
            git push --set-upstream origin "$BRANCH_LOCAL" || erro "Falha ao configurar upstream!"
        fi
        echo "✅ Pull + Merge + Push automatizado concluído!"
        ;;
    6)
        # Sair
        echo "Saindo..."
        exit 0
        ;;
    *)
        # Opção inválida
        echo "❌ Opção inválida! Tente novamente."
        ;;
esac

