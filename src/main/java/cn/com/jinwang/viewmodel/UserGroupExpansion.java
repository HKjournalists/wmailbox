package cn.com.jinwang.viewmodel;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;

import cn.com.jinwang.domain.UserGroup;

public class UserGroupExpansion  implements Set<UserGroup>, Serializable
{
  private static final long serialVersionUID = 1L;

  private static MetaDataKey<UserGroupExpansion> KEY = new MetaDataKey<UserGroupExpansion>()
  {
      private static final long serialVersionUID = 1L;
  };

  private Set<Long> ids = new HashSet<>();

  private boolean inverse;

  public void expandAll()
  {
      ids.clear();

      inverse = true;
  }

  public void collapseAll()
  {
      ids.clear();

      inverse = false;
  }

  @Override
  public boolean add(UserGroup foo)
  {
      if (inverse)
      {
          return ids.remove(foo.getId());
      }
      else
      {
          return ids.add(foo.getId());
      }
  }

  @Override
  public boolean remove(Object o)
  {
      UserGroup foo = (UserGroup)o;

      if (inverse)
      {
          return ids.add(foo.getId());
      }
      else
      {
          return ids.remove(foo.getId());
      }
  }

  @Override
  public boolean contains(Object o)
  {
    UserGroup foo = (UserGroup)o;

      if (inverse)
      {
          return !ids.contains(foo.getId());
      }
      else
      {
          return ids.contains(foo.getId());
      }
  }

  @Override
  public void clear()
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public int size()
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public boolean isEmpty()
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public <A> A[] toArray(A[] a)
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<UserGroup> iterator()
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray()
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection<?> c)
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends UserGroup> c)
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
      throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
      throw new UnsupportedOperationException();
  }

  /**
   * Get the expansion for the session.
   * 
   * @return expansion
   */
  public static UserGroupExpansion get()
  {
    UserGroupExpansion expansion = Session.get().getMetaData(KEY);
      if (expansion == null)
      {
          expansion = new UserGroupExpansion();

          Session.get().setMetaData(KEY, expansion);
      }
      return expansion;
  }
}
