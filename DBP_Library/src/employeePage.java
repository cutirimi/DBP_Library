import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class employeePage {
    public static void main(String userName) {
        JFrame frame = new JFrame("DBP Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // String userName = "홍길동"; // 사용자 이름
        String titleSuffix = "님 근로 Page";

        JLabel titleLabel = new JLabel(userName + titleSuffix, SwingConstants.CENTER);
        titleLabel.setFont(new Font("돋움", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        JLabel logoutLabel = new JLabel("<html><u>로그아웃</u></html>");
        logoutLabel.setBounds(320, 10, 50, 30);
        logoutLabel.setForeground(Color.BLACK); // 파란색 텍스트
        logoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 손가락 커서
        frame.add(logoutLabel);

        // 로그아웃 클릭시 로그인 페이지로 이동
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                login.main(null);
            }
        });

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        frame.add(titlePanel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel workInfoPanel = new JPanel();
        workInfoPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 105, 10)); // 가로 간격 20, 세로 간격 10
        JButton startButton = new JButton("근무시작");
        JButton endButton = new JButton("근무종료");
        buttonPanel.add(startButton);
        buttonPanel.add(endButton);
        workInfoPanel.add(buttonPanel, BorderLayout.NORTH);

        // 근무 정보 테이블
        String[] columns = {"근무번호", "근무일자", "근무시간", "일일급여"};
        DefaultTableModel workInfoModel = new DefaultTableModel(columns, 0);
        JTable workInfoTable = new JTable(workInfoModel);
        JScrollPane workInfoScrollPane = new JScrollPane(workInfoTable); // 테이블에 스크롤 추가
        workInfoPanel.add(workInfoScrollPane, BorderLayout.CENTER);

        // 샘플 데이터 추가
        workInfoModel.addRow(new Object[]{"001", "2023-11-01", "8시간", "80,000원"});
        workInfoModel.addRow(new Object[]{"002", "2023-11-02", "6시간", "60,000원"});

        tabbedPane.addTab("근무정보", workInfoPanel);

        // 월급조회 탭
        JPanel salaryPanel = new JPanel();
        salaryPanel.setLayout(null); // 절대 위치를 지정하기 위해 레이아웃을 null로 설정

        JComboBox<String> yearCombo = new JComboBox<>(new String[]{"2023", "2024", "2025"});
        yearCombo.setBounds(50, 50, 100, 30); // x, y, width, height
        JComboBox<String> monthCombo = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        monthCombo.setBounds(200, 50, 100, 30); // x, y, width, height

        JLabel yearLabel = new JLabel("년");
        yearLabel.setBounds(160, 50, 20, 30); // x, y, width, height
        JLabel monthLabel = new JLabel("월");
        monthLabel.setBounds(310, 50, 20, 30); // x, y, width, height

        salaryPanel.add(yearCombo);
        salaryPanel.add(yearLabel);
        salaryPanel.add(monthCombo);
        salaryPanel.add(monthLabel);

        JButton queryButton = new JButton("월급 조회");
        queryButton.setBounds(135, 150, 100, 30); // 버튼 위치 및 크기 설정
        salaryPanel.add(queryButton);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("돋움", Font.BOLD, 14));
        resultLabel.setBounds(50, 200, 300, 60); // 라벨 위치 및 크기 설정
        salaryPanel.add(resultLabel);

        queryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String year = (String) yearCombo.getSelectedItem();
                String month = (String) monthCombo.getSelectedItem();
                String time = "160"; // 총 근무시간
                String pay = "1,600,000"; // 월급
                resultLabel.setText("<html>" + year + "년 " + month + "월<br>총 " + time + "시간 근무<br>월급 " + pay + "원</html>");
            }
        });

        tabbedPane.addTab("월급조회", salaryPanel);

        // 연체자조회 탭
        JPanel overduePanel = new JPanel();
        overduePanel.setLayout(new BorderLayout());

        String[] overdueColumns = {"학번/교번", "연체자명", "연체일", "대출불가일"};
        DefaultTableModel overdueModel = new DefaultTableModel(overdueColumns, 0);
        JTable overdueTable = new JTable(overdueModel);
        JScrollPane overdueScrollPane = new JScrollPane(overdueTable); // 스크롤 추가

        // 샘플 데이터 추가
        overdueModel.addRow(new Object[]{"20231234", "김철수", "2023-11-20", "2023-12-01"});
        overdueModel.addRow(new Object[]{"20231235", "박영희", "2023-11-15", "2023-11-30"});

        overduePanel.add(overdueScrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("연체자조회", overduePanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
