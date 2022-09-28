package clantask;

import clantask.Sqlite.Clans;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author stankevich
 */
public class View extends JPanel {

    Clans clans;
    private int first;
    private int second;

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(View.class.getName());

    public View() {
        clans = new Clans();
        initComponents();
        setFrame();
        initClans();
        initLabels();
        if (nameClanField.getText().isEmpty()) {
            getClanInfo();
        }
    }

    private void setFrame() {
        jFrame1.show();
        jFrame1.setSize(540, 300);
        jFrame1.setLocationRelativeTo(this);
        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void createClan() throws SQLException, ClassNotFoundException {
        if (!nameClanField.getText().isEmpty()) {
            labelCreateClan.setText("Укажите название клана: ");
            clans.setName(nameClanField.getText());
            clans.insert();
            enableComponents(true);
            log.info("Создание клана c именем: " + clans.getName());
        } else {
            labelCreateClan.setText("Пустое имя клана!");
        }
    }

    private void initClans() {
        try {
            ArrayList<Clans> items = Clans.getAll();
            if (!items.isEmpty()) {
                clansCmb.setModel(new javax.swing.DefaultComboBoxModel(items.toArray()));
            } else {
                enableComponents(false);
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void getClanInfo() {
        lblIdClan.setText(((Clans) clansCmb.getSelectedItem()).getId());
        lblNameClan.setText(((Clans) clansCmb.getSelectedItem()).getName());
        lblGoldClan.setText(String.valueOf(((Clans) clansCmb.getSelectedItem()).getGold()));
        selectedClanlbl.setText("Клан: " + ((Clans) clansCmb.getSelectedItem()).getName());
    }

    private synchronized void setClanGold(String value) {
            try {
                Clans clan = (Clans) clansCmb.getSelectedItem();
                long gold = clan.getGold();
                clan.setGold(gold + Integer.parseInt(value));  
                clan.update();           
                lblGoldClan.setText(String.valueOf(clan.getGold()));
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void enableComponents(boolean switcher) {
        clansCmb.setEnabled(switcher);
        btnSetGold.setEnabled(switcher);
        setGoldField.setEnabled(switcher);
        answerField.setEnabled(switcher);
        answerButton.setEnabled(switcher);
    }

    private void initLabels() {
        first = (int) (Math.random() * 100);
        firslLabel.setText(String.valueOf(first));
        second = (int) (Math.random() * 100);
        secondLabel.setText(String.valueOf(second));
    }

    private void checkAnswer() throws SQLException, ClassNotFoundException {
        Clans clan = (Clans) clansCmb.getSelectedItem();
        if (!answerField.getText().isEmpty()) {
            if (first + second == Integer.parseInt(answerField.getText())) {
                setClanGold(String.valueOf("100"));
                log.info("Задание выполнено от клана: " + clan.getName() + ". " + "+100 золотых");
                
            } else {
                setClanGold(String.valueOf("-100"));
                log.info("Задание провалено от клана: " + clan.getName() + ". " + "-100 золотых");
            }
            initLabels();
        }
    }

    private void blockChars(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) || answerField.getText().length() >= 5) {
            evt.consume(); // consume non-numbers
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        addGoldTab = new javax.swing.JPanel();
        clansCmb = new javax.swing.JComboBox<>();
        btnSetGold = new javax.swing.JButton();
        setGoldField = new javax.swing.JTextField();
        lblIdClan = new javax.swing.JLabel();
        lblNameClan = new javax.swing.JLabel();
        lblGoldClan = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        newClanTab = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        labelCreateClan = new javax.swing.JLabel();
        nameClanField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        buttonCreateClan = new javax.swing.JButton();
        questTab = new javax.swing.JPanel();
        firslLabel = new javax.swing.JLabel();
        secondLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        answerField = new javax.swing.JTextField();
        answerButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        selectedClanlbl = new javax.swing.JLabel();

        clansCmb.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        clansCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Выберите клан" }));
        clansCmb.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                clansCmbPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                clansCmbPopupMenuWillBecomeVisible(evt);
            }
        });

        btnSetGold.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSetGold.setText("<html>Пополнить золото<br>Из 100 потоков");
        btnSetGold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSetGoldMouseClicked(evt);
            }
        });

        setGoldField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setGoldFieldKeyTyped(evt);
            }
        });

        idLabel.setText("ID Клана");

        jLabel5.setText("Имя клана");

        jLabel6.setText("Золото");

        javax.swing.GroupLayout addGoldTabLayout = new javax.swing.GroupLayout(addGoldTab);
        addGoldTab.setLayout(addGoldTabLayout);
        addGoldTabLayout.setHorizontalGroup(
            addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addGoldTabLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(clansCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addGoldTabLayout.createSequentialGroup()
                        .addComponent(btnSetGold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(setGoldField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addGoldTabLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addGoldTabLayout.createSequentialGroup()
                                .addComponent(lblIdClan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addGoldTabLayout.createSequentialGroup()
                                .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)))
                        .addGroup(addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNameClan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGoldClan, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        addGoldTabLayout.setVerticalGroup(
            addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addGoldTabLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGoldClan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNameClan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdClan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clansCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(addGoldTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addGoldTabLayout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(setGoldField, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSetGold))
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab("Пополнение золота", addGoldTab);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2, 40, 0));

        labelCreateClan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelCreateClan.setText("Укажите название клана:");
        jPanel5.add(labelCreateClan);

        nameClanField.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nameClanField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameClanFieldKeyTyped(evt);
            }
        });
        jPanel5.add(nameClanField);

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLabel7.setText("<html>Примечание!<br>Клан создается с нулевым показателем золота!");

        buttonCreateClan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        buttonCreateClan.setText("Создать");
        buttonCreateClan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCreateClanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout newClanTabLayout = new javax.swing.GroupLayout(newClanTab);
        newClanTab.setLayout(newClanTabLayout);
        newClanTabLayout.setHorizontalGroup(
            newClanTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newClanTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newClanTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(newClanTabLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(buttonCreateClan, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        newClanTabLayout.setVerticalGroup(
            newClanTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newClanTabLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(newClanTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCreateClan, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Создание клана", newClanTab);

        firslLabel.setText("jLabel1");

        secondLabel.setText("jLabel2");

        jLabel3.setText("+");

        jLabel9.setText("=");

        answerField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                answerFieldKeyTyped(evt);
            }
        });

        answerButton.setText("Ответить");
        answerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                answerButtonMouseClicked(evt);
            }
        });

        jLabel10.setText("<html>Примечание!<br>За правильный ответ клан получит +100 золота.<br> За неправильный -100");

        selectedClanlbl.setText("Клан:");

        javax.swing.GroupLayout questTabLayout = new javax.swing.GroupLayout(questTab);
        questTab.setLayout(questTabLayout);
        questTabLayout.setHorizontalGroup(
            questTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questTabLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(questTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(questTabLayout.createSequentialGroup()
                        .addComponent(selectedClanlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(questTabLayout.createSequentialGroup()
                        .addGroup(questTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(questTabLayout.createSequentialGroup()
                                .addComponent(firslLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel3)
                                .addGap(50, 50, 50)
                                .addComponent(secondLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel9))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(questTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(answerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
        );
        questTabLayout.setVerticalGroup(
            questTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questTabLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(selectedClanlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(questTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firslLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(questTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(questTabLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18))
                    .addGroup(questTabLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(answerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(85, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Задания", questTab);

        jFrame1.getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateClanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCreateClanMouseClicked
        try {
            createClan();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCreateClanMouseClicked

    private void nameClanFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameClanFieldKeyTyped
        if (nameClanField.getText().length() >= 11) { // limit textfield to 11 characters      
            evt.consume();
        }
    }//GEN-LAST:event_nameClanFieldKeyTyped

    private void clansCmbPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_clansCmbPopupMenuWillBecomeVisible
        initClans();
    }//GEN-LAST:event_clansCmbPopupMenuWillBecomeVisible

    private void clansCmbPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_clansCmbPopupMenuWillBecomeInvisible
        getClanInfo();
    }//GEN-LAST:event_clansCmbPopupMenuWillBecomeInvisible

    private void btnSetGoldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSetGoldMouseClicked
        Clans clan = (Clans) clansCmb.getSelectedItem();
        for (int x = 0; x < 100; x++) {
            try {
                Thread temp = new Thread(() -> {
                    setClanGold(String.valueOf(setGoldField.getText()));
                    log.info("Пополнение золота на " + setGoldField.getText() + " у клана: " + clan.getName() + ", c id: " + clan.getId());
                }, "setClanGold");
                temp.start();
                temp.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSetGoldMouseClicked

    private void setGoldFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setGoldFieldKeyTyped
        blockChars(evt);
    }//GEN-LAST:event_setGoldFieldKeyTyped

    private void answerButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_answerButtonMouseClicked
        try {
            checkAnswer();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_answerButtonMouseClicked

    private void answerFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_answerFieldKeyTyped
        blockChars(evt);
    }//GEN-LAST:event_answerFieldKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addGoldTab;
    private javax.swing.JButton answerButton;
    private javax.swing.JTextField answerField;
    private javax.swing.JButton btnSetGold;
    private javax.swing.JButton buttonCreateClan;
    private javax.swing.JComboBox<String> clansCmb;
    private javax.swing.JLabel firslLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCreateClan;
    private javax.swing.JLabel lblGoldClan;
    private javax.swing.JLabel lblIdClan;
    private javax.swing.JLabel lblNameClan;
    private javax.swing.JTextField nameClanField;
    private javax.swing.JPanel newClanTab;
    private javax.swing.JPanel questTab;
    private javax.swing.JLabel secondLabel;
    private javax.swing.JLabel selectedClanlbl;
    private javax.swing.JTextField setGoldField;
    // End of variables declaration//GEN-END:variables

}
