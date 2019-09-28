package pe.com.maquistemas.extornoJpa.oauth.services;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Service;

import oracle.jdbc.OracleTypes;
import pe.com.maquistemas.extornoJpa.entity.MqPersona;

@Service
public class PlSqlService implements IPlSqlService {
	
	@PersistenceContext
	private EntityManager em;
	
	

	@Override
	public String sp_transferencia(Integer PAR_TRANSF_USUARIO_ID, Integer PAR_TRANSF_CUENTA_ORIGEN, Integer PAR_TRANSF_CUENTA_DESTINO, BigDecimal PAR_TRANSF_MONTO) {
		
		Session session = em.unwrap(Session.class);
		CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
			@Override
			public CallableStatement execute(Connection connection) throws SQLException {
				CallableStatement function = connection.prepareCall("{ call PACK_TRANSACCION_MQ.SP_TRANSFERENCIA(?,?,?,?) }");
				function.setInt(1, PAR_TRANSF_USUARIO_ID);
				function.setInt(2, PAR_TRANSF_CUENTA_ORIGEN);
				function.setInt(3, PAR_TRANSF_CUENTA_DESTINO);
				function.setBigDecimal(4, PAR_TRANSF_MONTO);
				//register the OUT parameter before calling the stored procedure
				//function.registerOutParameter(3, Types.INTEGER);

				function.execute();
				
				//function.executeUpdate(); //para sp_insertar
				
				//read the OUT parameter now
//				Integer result = function.getInt(3);
//				System.out.println("resultado: "+result);
				
				return function;
			}
		});

		try {
			//return "" + callableStatement.getInt(3);
			return "La transferencia se realizó con exito";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	@Override
	public String sp_extorno(Integer PAR_TRANSF_ID, Integer PAR_USUARIO_ID) {

		Session session = em.unwrap(Session.class);
		CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
			@Override
			public CallableStatement execute(Connection connection) throws SQLException {
				CallableStatement function = connection.prepareCall("{ call PACK_TRANSACCION_MQ.SP_EXTORNO(?,?) }");
				function.setInt(1, PAR_TRANSF_ID);
				function.setInt(2, PAR_USUARIO_ID);
				
				//register the OUT parameter before calling the stored procedure
				//function.registerOutParameter(3, Types.INTEGER);

				function.execute();
				
				//function.executeUpdate(); //para sp_insertar
				
				//read the OUT parameter now
//				Integer result = function.getInt(3);
//				System.out.println("resultado: "+result);
				
				return function;
			}
		});

		try {
			//return "" + callableStatement.getInt(3);
			return "El extorno se realizó con exito";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Integer sf_suma(Integer a, Integer b) {
		
		Session session = em.unwrap(Session.class);
		CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
			@Override
			public CallableStatement execute(Connection connection) throws SQLException {
				CallableStatement function = connection.prepareCall("{ ? = call PACK_TRANSACCION_MQ.SF_SUMA(?,?) }");
				function.registerOutParameter(1, Types.INTEGER);
				function.setInt(2, a);
				function.setInt(3, b);

				function.execute();
				return function;
			}
		});

		try {
			return callableStatement.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	
	}

	@Override
	public Integer fn_get_all_tb_usuario() {
//		Session session = em.unwrap(Session.class);
//		CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
//			@Override
//			public CallableStatement execute(Connection connection) throws SQLException {
//				CallableStatement function = connection.prepareCall("{ ? = call fn_get_all_tb_usuario() }");
////				function.registerOutParameter(1, Types.REF_CURSOR);
//				function.registerOutParameter(1, OracleTypes.CURSOR);
//
//				function.execute();
//				return function;
//			}
//		});
//
//		try {
//			//return callableStatement.getResultSet(1);
//			return = ((OracleCallableStatement)function).getCursor(2);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
		return null;
	}

	@Override
	public List<MqPersona> sp_get_all_mqpersonas(String p_nombre) {
		Session session = em.unwrap(Session.class);
		List<MqPersona> mqPersonas = new ArrayList<>();
		
		CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
			@Override
			public CallableStatement execute(Connection connection) throws SQLException {
				//CallableStatement function = connection.prepareCall("BEGIN call sp_get_all_mqpersonas(?, ?); END;");
				CallableStatement function = connection.prepareCall("{ call sp_get_all_mqpersonas(?, ?) }");
				function.setString(1, p_nombre);
				
				//register the OUT parameter before calling the stored procedure
				function.registerOutParameter(2, OracleTypes.CURSOR);

				function.execute();
				
				//function.executeUpdate(); //para sp_insertar
				
				//read the OUT parameter now
//				Integer result = function.getInt(3);
			//ResultSet rs = ((OracleCallableStatement)function).getCursor(2);
			
			ResultSet rs = (ResultSet) function.getObject(2);
			
			 while(rs.next()){
			       MqPersona p = new MqPersona();
			       p.setId(rs.getLong(1));
			       p.setNombre(rs.getString("NOMBRE"));
			       p.setApellido(rs.getString("APELLIDO"));
			       mqPersonas.add(p);
			   }
				
				return function;
			}
		});

		try {
			//return "" + callableStatement.getInt(3);
			//return "El extorno se realizó con exito";
			return mqPersonas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
