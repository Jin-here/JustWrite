## 配置git
1. git config --global user.name "Your name"
2. git config --global user.email your@email.com

## 其他
1. 避免重复用户名密码输入：git config --global credential.helper store
2. [git flow](https://www.git-tower.com/learn/git/ebook/cn/command-line/advanced-topics/git-flow)

## stash（stash以栈方式存储）
- git stash list：列出所有stash，最上面的为时间最近的stash
- git stash：以默认名称保存stash
- git stash save "msg"：以msg保存stash
- apply
  - git stash apply：恢复最近的一个stash
  - git stash apply 名称：恢复名称对应的stash(栈中stash不删除)
- pop
  - git stash pop：同3，同时删除栈中的stash
  - git stash pop 名称：同3，同时删除栈中的stash
- drop
- git stash drop：删除最近的一个stash
- git stash drop 名称：删除指定名称的stash

## 项目迁移
1. 更新服务端所以数据至本地：git fetch
2. 执行 git remote set-url origin URL(其中URL为新项目的git路径)
3. git push -all

## tag
- 含附注的标签
git tag -a v1.4 -m 'version 1.4'
- 后期加注标签
git tag -a v1.2 9fceb02
- 标签提交
  - 单个：git push origin v1.4
  - 所有：git push origin --tags

## clean
- git clean: del untracked files
- git clean -fd: del untracked files and dirs
- git clean -xfd: del untracked files and dirs(no matther is in .gitignore)
- git clean clean -n(例如-nf, -nfd, -nxfd): to have a look at which files whill be del