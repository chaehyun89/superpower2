package Gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import Communication.ServerThread;
import Database.DbConnector;
import Database.ResetLogFlag;

/**
 * 메인프레임 클래스. 각 기능별 패널들을 담는 큰 틀.
 * 
 * @author Minji, Seongjun
 * @since 2015/5/1
 * @version 2015/5/20
 */
public class MainFrame extends JFrame {

	private JPanel contentPane; // 디폴트로 있음

	private MemberPanel memberPanel; // 회원 패널
	private CouponPanel couponPanel; // 쿠폰 패널
	private ItemPanel itemPanel; // 상품 패널
	private OwnershipPanel ownershipPanel; // 소유 패널
	private PurchasePanel purchasePanel; // 구매 패널
	private BeaconPanel beaconPanel;	// 비콘 패널

	private ServerThread serverThread; // 서버 클래스

	/**
	 * 메인 함수.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 메인프레임 GUI 창 띄움
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 생성자. 메인함수 수행시 호출됨.
	 */
	public MainFrame() {
		setTitle("Super Power Server");
		setResizable(false);

		// 메인 프레임의 Swing 요소 설정
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 창 종료시 이벤트
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				closeForm();
			}
		});

		// 각 기능별 패널을 담을 텝 패널 설정
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 10, 760, 542);
		contentPane.add(tabbedPane);

		// 회원 패널 설정
		memberPanel = new MemberPanel();
		tabbedPane.addTab("회원", memberPanel);

		// 쿠폰 패널 설정
		couponPanel = new CouponPanel();
		tabbedPane.addTab("쿠폰", couponPanel);

		// 상품 패널 설정
		itemPanel = new ItemPanel();
		tabbedPane.addTab("상품", itemPanel);

		// 소유 패널 설정
		ownershipPanel = new OwnershipPanel();
		tabbedPane.addTab("소유", ownershipPanel);

		// 구매 패널 설정
		purchasePanel = new PurchasePanel();
		tabbedPane.addTab("구매", purchasePanel);

		// 비콘 패널 설정
		beaconPanel = new BeaconPanel();
		tabbedPane.addTab("비콘", beaconPanel);

		// 서버 시작
		this.serverThread = new ServerThread();
		this.serverThread.startServer();

		// 로그인여부 초기화
		try {
			ResetLogFlag.doAction();
		} catch (SQLException e) {
			System.out.println("MainFrame()에서 예외 발생 : " + e.getMessage());
		}
	}

	/**
	 * 프로그램 종료전 서버 스레드 정리부터 하고 종료함.
	 */
	@SuppressWarnings("deprecation")
	public void closeForm() {
		// 종료 확인 여부 창 띄움
		int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

		// no 이면 return
		if (res != 0)
			return;

		// 서버 종료
		if (this.serverThread.isRunning()) {
			this.serverThread.stop();
		}
		this.serverThread.stopServer();

		// 디비 종료
		DbConnector.getInstance().closeConnection();

		// 프로그램 종료
		System.exit(0);
	}
}
