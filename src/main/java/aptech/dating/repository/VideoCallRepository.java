package aptech.dating.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aptech.dating.model.VideoCall;


public interface VideoCallRepository extends JpaRepository<VideoCall, Integer> {
}
