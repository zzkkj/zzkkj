#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
const int N=32767,inf=1e7;
struct node{
    int l,r;
    int key,val;
}tr[N];
int root,idx;
int get_node(int key){
    tr[++idx].key=key;
    tr[idx].val=rand();
    return idx;
}
void build(){
    get_node(-inf);
    get_node(inf);
    tr[1].r=2;
}
void zuoxuan(int &p){
    int q=tr[p].r;
    tr[p].r=tr[q].l,tr[q].l=p,p=q;
}
void youxuan(int &p){
    int q=tr[p].l;
    tr[p].l=tr[q].r,tr[q].r=p,p=q;

}
void insert(int &p,int key){
    if(!p)p=get_node(key);
    else if(tr[p].key==key)return ;
    else if(tr[p].key>key){
        insert(tr[p].l,key);
        if(tr[tr[p].l].val>tr[p].val)youxuan(p);
    }
    else {
        insert(tr[p].r,key);
        if(tr[tr[p].r].val>tr[p].val)zuoxuan(p);
    }
}
int get_prev(int p,int key){
    if(!p)return -inf;
     if(tr[p].key>key)return get_prev(tr[p].l,key);
    return max(tr[p].key,get_prev(tr[p].r,key));
}
int get_next(int p,int key){
    if(!p)return inf;
    if(tr[p].key<key)return get_next(tr[p].r,key);
    return min(tr[p].key,get_next(tr[p].l,key)); 
}
int main(){
    int n;
    scanf("%d",&n); 
    ll  res=0;
    for(int i=1;i<=n;i++){
        int x;
        scanf("%d",&x);
        if(i==1)res+=x;
        else{
            res+=min(x-get_prev(root,x),get_next(root,x)-x);
        }
        insert(root,x);
    }
    printf("%lld",res);
}

