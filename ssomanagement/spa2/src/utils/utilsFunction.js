export const isAdminCheck = function(roles){
    let i;
    for (i = 0; i< roles.length;i++){
        if (roles[i].authority == 'ROLE_ADMIN'){
            return true;
        }
    }
    return false;
};