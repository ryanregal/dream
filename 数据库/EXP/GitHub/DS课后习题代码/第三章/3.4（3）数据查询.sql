USE `db3.4`;
/*求供应工程J1零件的供应商号码SNO*/
SELECT SNO FROM SPJ WHERE JNO='J1';

/*求供应工程J1零件P1的供应商号码SNO*/
SELECT SNO FROM SPJ WHERE JNO='J1'
AND PNO='P1';

/*求供应工程J1零件为红色的供应商号码SNO*/
SELECT SNO FROM SPJ,P 
WHERE JNO='J1' AND P.PNO =SPJ.PNO AND COLOR='红';

/*求没有天津供应商生产的红色零件的工程号JNO*/ 
SELECT JNO FROM J WHERE NOT EXISTS 
(SELECT *FROM SPJ,S,P/*这里的子查询是一个多表连接*/
WHERE SPJ.JNO=J.JNO AND SPJ.SNO=S.SNO
AND SPJ.PNO=P.PNO AND S.CITY='天津'
AND P.COLOR='红');
