package Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MemberDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField textFieldId;
	private JPasswordField passwordFieldPassword;
	private JTextField textFieldName;
	private JComboBox<String> comboBoxSex;
	private JSpinner spinnerAge;
	private JTextField textFieldFavorite;
	private JSpinner spinnerEnterCount;
	
	private boolean ok = false;

	/**
	 * 생성자. 회원 추가 버튼 누를 시 호출
	 */
	public MemberDialog() {
		
		// 다이얼로그 속성 설정
		setTitle("\uD68C\uC6D0 \uAD00\uB9AC");
		setModal(true);
		setBounds(0, 0, 262, 259);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// ContentPane 설정
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// ID 레이블
		JLabel labelId = new JLabel("ID :");
		labelId.setHorizontalAlignment(SwingConstants.RIGHT);
		labelId.setBounds(12, 10, 95, 15);
		contentPanel.add(labelId);

		// ID 텍스트필드
		textFieldId = new JTextField();
		textFieldId.setBounds(119, 7, 116, 21);
		contentPanel.add(textFieldId);
		textFieldId.setColumns(10);

		// 비밀번호 레이블
		JLabel labelPassword = new JLabel("\uBE44\uBC00\uBC88\uD638 :");
		labelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPassword.setBounds(12, 35, 95, 15);
		contentPanel.add(labelPassword);

		// 비밀번호 필드
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(119, 32, 116, 21);
		contentPanel.add(passwordFieldPassword);

		// 이름 레이블
		JLabel labelName = new JLabel("\uC774\uB984 :");
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelName.setBounds(12, 60, 95, 15);
		contentPanel.add(labelName);

		// 이름 필드
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(119, 57, 116, 21);
		contentPanel.add(textFieldName);

		// 성별 레이블
		JLabel labelSex = new JLabel("\uC131\uBCC4 :");
		labelSex.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSex.setBounds(12, 85, 95, 15);
		contentPanel.add(labelSex);

		// 성별 콤보박스
		comboBoxSex = new JComboBox<String>();
		comboBoxSex.setModel(new DefaultComboBoxModel<String>(new String[] { "\uB0A8",
				"\uC5EC" }));
		comboBoxSex.setSelectedIndex(0);
		comboBoxSex.setBounds(119, 82, 59, 21);
		contentPanel.add(comboBoxSex);
		
		// 나이 레이블
		JLabel labelAge = new JLabel("\uB098\uC774 :");
		labelAge.setHorizontalAlignment(SwingConstants.RIGHT);
		labelAge.setBounds(12, 110, 95, 15);
		contentPanel.add(labelAge);

		// 나이 스피너
		spinnerAge = new JSpinner();
		spinnerAge.setModel(new SpinnerNumberModel(new Integer(1), new Integer(
				1), null, new Integer(1)));
		spinnerAge.setBounds(119, 107, 59, 22);
		contentPanel.add(spinnerAge);
		
		// 관심사 레이블
		JLabel labelFavorite = new JLabel("\uAD00\uC2EC\uC0AC :");
		labelFavorite.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFavorite.setBounds(12, 135, 95, 15);
		contentPanel.add(labelFavorite);
		
		// 관심사 필드
		textFieldFavorite = new JTextField();
		textFieldFavorite.setBounds(119, 132, 116, 21);
		contentPanel.add(textFieldFavorite);
		textFieldFavorite.setColumns(10);
		
		// 방문횟수 레이블
		JLabel labelEnterCount = new JLabel("\uBC29\uBB38\uD69F\uC218 :");
		labelEnterCount.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEnterCount.setBounds(12, 160, 95, 15);
		contentPanel.add(labelEnterCount);
		
		// 방문횟수 스피너
		spinnerEnterCount = new JSpinner();
		spinnerEnterCount.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));
		spinnerEnterCount.setBounds(119, 157, 59, 22);
		contentPanel.add(spinnerEnterCount);
		
		// 확인, 취소버튼 (Default)
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\uD655\uC778");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ok = true;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\uCDE8\uC18C");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		// 창 보여줌.
		setVisible(true);
	}
	
	/**
	 * 생성자. 회원 수정버튼 누를 시 호출
	 * 
	 * @param id
	 */
	public MemberDialog(String id) {
		this();
		
		// 디비를 통해 정보를 받은 후 필드 설정
		// 
	}
	
	/**
	 * 입력한 정보들을 반환
	 * 
	 * @return information list
	 */
	public List<String> getInformations() {
		
		List<String> infoList = new ArrayList<String>();
		infoList.add(textFieldId.getText());
		infoList.add(String.valueOf(passwordFieldPassword.getPassword()));
		infoList.add(textFieldName.getText());
		
		String sex = "남".equals((String) comboBoxSex.getSelectedItem())?"m":"f"; 
		infoList.add(sex);
		
		infoList.add(Integer.toString((Integer) spinnerAge.getValue()));
		infoList.add(textFieldFavorite.getText());
		infoList.add(Integer.toString((Integer) spinnerEnterCount.getValue()));		
		
		return infoList;
	}
	
	/**
	 * 확인, 취소 버튼 눌렀는지 여부 반혼
	 * 
	 * @return ok
	 */
	public boolean isOk() {
		return this.ok;
	}
}
