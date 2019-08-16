/**
 * Created by
 *  email   :
 */
/**生成代码*/
function generator(table_id){

    var tableNames = getSelectedRows(table_id);
    if(tableNames == null){
        return ;
    }
    location.href = "/yzweb/sys/generator/code?tables=" + JSON.stringify(tableNames);
}
